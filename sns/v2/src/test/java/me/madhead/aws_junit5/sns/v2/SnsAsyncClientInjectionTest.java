package me.madhead.aws_junit5.sns.v2;

import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.sns.SnsAsyncClient;

@ExtendWith(SNS.class)
class SnsAsyncClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private SnsAsyncClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        // Assertions.assertEquals(
        //     Collections.singletonList("topic"),
        //     client
        //         .listTopics()
        //         .get()
        //         .topics()
        //         .stream()
        //         .map(Topic::topicArn)
        //         .sorted()
        //         .collect(Collectors.toList())
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
