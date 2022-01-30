package me.madhead.aws_junit5.dynamo.v2;

import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.dynamodb.model.Stream;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDB.class)
class DynamoDbStreamsClientInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private DynamoDbStreamsClient client;

    @Test
    void test() {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            client
                .listStreams()
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
            return System.getenv("DYNAMODB_STREAMS_URL");
        }

        @Override
        public String region() {
            return System.getenv("DYNAMODB_STREAMS_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("DYNAMODB_STREAMS_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("DYNAMODB_STREAMS_SECRET_KEY");
        }
    }
}
