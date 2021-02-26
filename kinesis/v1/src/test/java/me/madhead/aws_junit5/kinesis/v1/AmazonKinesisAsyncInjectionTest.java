package me.madhead.aws_junit5.kinesis.v1;

import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import com.amazonaws.services.kinesis.AmazonKinesisAsync;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(Kinesis.class)
class AmazonKinesisAsyncInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private AmazonKinesisAsync client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("stream"),
            client
                .listStreamsAsync()
                .get()
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
            return System.getProperty("kinesis.url");
        }

        @Override
        public String region() {
            return System.getProperty("kinesis.region");
        }

        @Override
        public String accessKey() {
            return System.getProperty("kinesis.accessKey");
        }

        @Override
        public String secretKey() {
            return System.getProperty("kinesis.secretKey");
        }
    }
}
