package by.dev.madhead.aws_junit5.sqs.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(SQS.class)
class SqsAsyncClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private SqsAsyncClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("http://localhost:4576/queue/queue"),
            client
                .listQueues()
                .get()
                .queueUrls()
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getProperty("sqs.url");
        }

        @Override
        public String region() {
            return System.getProperty("sqs.region");
        }

        @Override
        public String accessKey() {
            return System.getProperty("sqs.accessKey");
        }

        @Override
        public String secretKey() {
            return System.getProperty("sqs.secretKey");
        }
    }
}
