package by.dev.madhead.aws_junit5.kinesis.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.firehose.FirehoseAsyncClient;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(Kinesis.class)
class FirehoseAsyncClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private FirehoseAsyncClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("delivery-stream"),
            client
                .listDeliveryStreams()
                .get()
                .deliveryStreamNames()
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
