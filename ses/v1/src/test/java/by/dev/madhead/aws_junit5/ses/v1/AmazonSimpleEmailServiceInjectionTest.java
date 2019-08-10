package by.dev.madhead.aws_junit5.ses.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(SES.class)
class AmazonSimpleEmailServiceInjectionTest {
    @AWSClient(
        clientConfiguration = ClientConfiguration.class
    )
    private AmazonSimpleEmailService client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        // Assertions.assertEquals(
        //     Collections.singletonList("topic"),
        //     client.listIdentities().getIdentities().stream().sorted().collect(Collectors.toList())
        // );
    }

    public static class ClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return System.getenv("SES_URL");
        }

        @Override
        public String region() {
            return System.getenv("SES_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("SES_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("SES_SECRET_KEY");
        }
    }
}
