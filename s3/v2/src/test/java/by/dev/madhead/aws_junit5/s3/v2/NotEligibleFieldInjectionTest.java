package by.dev.madhead.aws_junit5.s3.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;

class NotEligibleFieldInjectionTest {
    @AWSClient(endpoint = Endpoint.class)
    private String client;

    @Test
    void test() throws Exception {
        final IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new S3().postProcessTestInstance(this, null)
        );

        Assertions.assertEquals(
            "class java.lang.String is not supported by S3 extension.",
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
            return Region.US_EAST_1.id();
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
