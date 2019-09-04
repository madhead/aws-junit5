package by.dev.madhead.aws_junit5.kinesis.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseAsync;
import com.amazonaws.services.kinesisfirehose.model.ListDeliveryStreamsRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(Kinesis.class)
class AmazonKinesisFirehoseAsyncInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private AmazonKinesisFirehoseAsync client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("delivery-stream"),
            client
                .listDeliveryStreamsAsync(new ListDeliveryStreamsRequest())
                .get()
                .getDeliveryStreamNames()
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getProperty("firehose.url");
        }

        @Override
        public String region() {
            return System.getProperty("firehose.region");
        }

        @Override
        public String accessKey() {
            return System.getProperty("firehose.accessKey");
        }

        @Override
        public String secretKey() {
            return System.getProperty("firehose.secretKey");
        }
    }
}
