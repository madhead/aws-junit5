package me.madhead.aws_junit5.sns.v1;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(SNS.class)
class AmazonSNSInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private AmazonSNS client;

    @Test
    void test() {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("arn:aws:sns:us-east-1:000000000000:topic"),
            client.listTopics().getTopics().stream().map(Topic::getTopicArn).sorted().collect(Collectors.toList())
        );
    }

    public static class Endpoint implements AWSEndpoint {
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
