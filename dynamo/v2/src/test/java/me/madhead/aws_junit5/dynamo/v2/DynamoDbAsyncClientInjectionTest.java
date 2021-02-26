package me.madhead.aws_junit5.dynamo.v2;

import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDB.class)
class DynamoDbAsyncClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private DynamoDbAsyncClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            client
                .listTables()
                .get()
                .tableNames()
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class Endpoint implements AWSEndpoint {
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
