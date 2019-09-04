package by.dev.madhead.aws_junit5.ses.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.ses.SesClient;

@ExtendWith(SES.class)
class SesClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private SesClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        // Assertions.assertEquals(
        //     Collections.singletonList("topic"),
        //     client
        //         .listIdentities()
        //         .identities()
        //         .stream()
        //         .sorted()
        //         .collect(Collectors.toList())
        // );
    }

    public static class Endpoint implements AWSEndpoint {
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
