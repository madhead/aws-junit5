package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsAsync;
import com.amazonaws.services.dynamodbv2.model.ListStreamsRequest;
import com.amazonaws.services.dynamodbv2.model.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDB.class)
class AmazonDynamoDBStreamsAsyncInjectionTest {
    @AWSClient(
        clientConfiguration = ClientConfiguration.class
    )
    private AmazonDynamoDBStreamsAsync client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            client
                .listStreamsAsync(new ListStreamsRequest().withTableName("table"))
                .get()
                .getStreams()
                .stream()
                .map(Stream::getTableName)
                .sorted()
                .collect(Collectors.toList())
        );
    }

    public static class ClientConfiguration implements AWSClientConfiguration {
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
