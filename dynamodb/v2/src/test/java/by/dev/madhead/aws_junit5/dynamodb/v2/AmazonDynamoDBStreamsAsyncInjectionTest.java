package by.dev.madhead.aws_junit5.dynamodb.v2;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.dynamodb.model.Stream;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDBLocalExtension.class)
class AmazonDynamoDBStreamsAsyncInjectionTest {
    @DynamoDBLocal(
        serviceConfiguration = SystemPropertiesDynamoDBStreamsServiceConfiguration.class
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
}
