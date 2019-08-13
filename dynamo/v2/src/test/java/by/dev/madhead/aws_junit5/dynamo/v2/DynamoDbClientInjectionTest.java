package by.dev.madhead.aws_junit5.dynamo.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDB.class)
class DynamoDbClientInjectionTest {
    @AWSClient(
        clientConfiguration = ClientConfiguration.class
    )
    private DynamoDbClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            client
                .listTables()
                .tableNames()
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class ClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return System.getenv("DYNAMODB_URL");
        }

        @Override
        public String region() {
            return System.getenv("DYNAMODB_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("DYNAMODB_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("DYNAMODB_SECRET_KEY");
        }
    }
}
