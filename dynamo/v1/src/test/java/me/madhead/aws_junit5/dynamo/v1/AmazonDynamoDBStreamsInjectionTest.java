package me.madhead.aws_junit5.dynamo.v1;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreams;
import com.amazonaws.services.dynamodbv2.model.ListStreamsRequest;
import com.amazonaws.services.dynamodbv2.model.Stream;
import me.madhead.aws_junit5.common.AWSClient;
import me.madhead.aws_junit5.common.AWSEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDB.class)
class AmazonDynamoDBStreamsInjectionTest {
    @AWSClient(
        endpoint = Endpoint.class
    )
    private AmazonDynamoDBStreams client;

    @Test
    void test() {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            client
                .listStreams(new ListStreamsRequest().withTableName("table"))
                .getStreams()
                .stream()
                .map(Stream::getTableName)
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
