package by.dev.madhead.aws_junit5.kinesis.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.model.ListDeliveryStreamsRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(Kinesis.class)
class AmazonKinesisFirehoseInjectionTest {
    @AWSClient(
        clientConfiguration = ClientConfiguration.class
    )
    private AmazonKinesisFirehose client;

    @Test
    void test() {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("delivery-stream"),
            client
                .listDeliveryStreams(new ListDeliveryStreamsRequest())
                .getDeliveryStreamNames()
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class ClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return System.getenv("FIREHOSE_URL");
        }

        @Override
        public String region() {
            return System.getenv("FIREHOSE_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("FIREHOSE_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("FIREHOSE_SECRET_KEY");
        }
    }
}
