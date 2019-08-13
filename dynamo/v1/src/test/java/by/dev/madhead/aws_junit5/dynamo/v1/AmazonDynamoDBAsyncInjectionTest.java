package by.dev.madhead.aws_junit5.dynamo.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDB.class)
class AmazonDynamoDBAsyncInjectionTest {
    @AWSClient(
        clientConfiguration = ClientConfiguration.class
    )
    private AmazonDynamoDBAsync client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            client.listTablesAsync().get().getTableNames().stream().sorted().collect(Collectors.toList())
        );
    }

    public static class ClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return System.getProperty("dynamodb.url");
        }

        @Override
        public String region() {
            return System.getProperty("dynamodb.region");
        }

        @Override
        public String accessKey() {
            return System.getProperty("dynamodb.accessKey");
        }

        @Override
        public String secretKey() {
            return System.getProperty("dynamodb.secretKey");
        }
    }
}
