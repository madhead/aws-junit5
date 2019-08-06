package by.dev.madhead.aws_junit5.dynamodb.v1;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyUrlInjectionTest {
    public static final class ServiceConfiguration implements by.dev.madhead.aws_junit5.common.ServiceConfiguration {
        @Override
        public String url() {
            return "";
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

    @DynamoDBLocal(serviceConfiguration = ServiceConfiguration.class)
    private AmazonDynamoDB amazonDynamoDB;

    @Test
    @DisplayName("Invalid URL should cause an exception")
    void test() throws Exception {
        final IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new DynamoDBLocalExtension().postProcessTestInstance(this, null)
        );
    }
}
