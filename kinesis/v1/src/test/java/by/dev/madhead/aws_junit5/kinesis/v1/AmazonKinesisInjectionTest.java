package by.dev.madhead.aws_junit5.kinesis.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import com.amazonaws.services.kinesis.AmazonKinesis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(Kinesis.class)
class AmazonKinesisInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private AmazonKinesis client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("stream"),
            client
                .listStreams()
                .getStreamNames()
                .stream()
                .filter(s -> !s.startsWith("__"))
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getenv("KINESIS_URL");
        }

        @Override
        public String region() {
            return System.getenv("KINESIS_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("KINESIS_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("KINESIS_SECRET_KEY");
        }
    }
}
