package by.dev.madhead.aws_junit5.dynamodb.v2;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

class EmptyUrlInjectionTest {
    public static final class ServiceConfiguration implements by.dev.madhead.aws_junit5.common.ServiceConfiguration {
        @Override
        public String url() {
            return "";
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

    @DynamoDBLocal(serviceConfiguration = ServiceConfiguration.class)
    private DynamoDbClient client;

    @Test
    void test() throws Exception {
        final IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new DynamoDBLocalExtension().postProcessTestInstance(this, null)
        );
    }
}
