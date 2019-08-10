package by.dev.madhead.aws_junit5.sqs.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(SQS.class)
class AmazonSQSAsyncInjectionTest {
    @AWSClient(
        clientConfiguration = ClientConfiguration.class
    )
    private AmazonSQSAsync client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("http://localhost:4576/queue/queue"),
            client.listQueuesAsync().get().getQueueUrls().stream().sorted().collect(Collectors.toList())
        );
    }

    public static class ClientConfiguration implements AWSClientConfiguration {
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
