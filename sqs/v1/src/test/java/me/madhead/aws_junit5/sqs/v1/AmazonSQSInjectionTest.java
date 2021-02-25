package me.madhead.aws_junit5.sqs.v1;

import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import com.amazonaws.services.sqs.AmazonSQS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(SQS.class)
class AmazonSQSInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private AmazonSQS client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("http://localhost:4576/queue/queue"),
            client.listQueues().getQueueUrls().stream().sorted().collect(Collectors.toList())
        );
    }

    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getenv("SQS_URL");
        }

        @Override
        public String region() {
            return System.getenv("SQS_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("SQS_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("SQS_SECRET_KEY");
        }
    }
}
