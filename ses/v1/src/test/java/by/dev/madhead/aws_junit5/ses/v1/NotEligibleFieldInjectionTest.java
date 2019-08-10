package by.dev.madhead.aws_junit5.ses.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import com.amazonaws.regions.Regions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotEligibleFieldInjectionTest {
    @AWSClient(endpoint = Endpoint.class)
    private String client;

    @Test
    void test() throws Exception {
        final IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new SES().postProcessTestInstance(this, null)
        );

        Assertions.assertEquals(
            "class java.lang.String is not supported by SES extension.",
            exception.getMessage()
        );
    }

    public static final class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return "https://madhead.me";
        }

        @Override
        public String region() {
            return Regions.US_EAST_1.getName();
        }

        @Override
        public String accessKey() {
            return "accessKey";
        }

        @Override
        public String secretKey() {
            return "secretKey";
        }
    }
}
