package by.dev.madhead.aws_junit5.dynamo.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.dynamodb.model.Stream;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDB.class)
class DynamoDbStreamsAsyncClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private DynamoDbStreamsAsyncClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            client
                .listStreams()
                .get()
                .streams()
                .stream()
                .map(Stream::tableName)
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class Endpoint implements AWSEndpoint {
        @Override
        public String url() {
            return System.getProperty("dynamodbstreams.url");
        }

        @Override
        public String region() {
            return System.getProperty("dynamodbstreams.region");
        }

        @Override
        public String accessKey() {
            return System.getProperty("dynamodbstreams.accessKey");
        }

        @Override
        public String secretKey() {
            return System.getProperty("dynamodbstreams.secretKey");
        }
    }
}
