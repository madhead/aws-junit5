package by.dev.madhead.aws_junit5.sns.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import com.amazonaws.services.sns.AmazonSNSAsync;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SNS.class)
class AmazonSNSAsyncInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private AmazonSNSAsync client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        // Assertions.assertEquals(
        //     Collections.singletonList("topic"),
        //     client.listTopicsAsync().get().getTopics().stream().map(Topic::getTopicArn).sorted().collect(Collectors.toList())
        // );
    }

    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getProperty("sns.url");
        }

        @Override
        public String region() {
            return System.getProperty("sns.region");
        }

        @Override
        public String accessKey() {
            return System.getProperty("sns.accessKey");
        }

        @Override
        public String secretKey() {
            return System.getProperty("sns.secretKey");
        }
    }
}
