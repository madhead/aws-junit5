package by.dev.madhead.aws_junit5.sns.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import com.amazonaws.services.sns.AmazonSNS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SNS.class)
class AmazonSNSInjectionTest {
    @AWSClient(
        clientConfiguration = ClientConfiguration.class
    )
    private AmazonSNS client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        // Assertions.assertEquals(
        //     Collections.singletonList("topic"),
        //     client.listTopics().getTopics().stream().map(Topic::getTopicArn).sorted().collect(Collectors.toList())
        // );
    }

    public static class ClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return System.getenv("SNS_URL");
        }

        @Override
        public String region() {
            return System.getenv("SNS_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("SNS_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("SNS_SECRET_KEY");
        }
    }
}
