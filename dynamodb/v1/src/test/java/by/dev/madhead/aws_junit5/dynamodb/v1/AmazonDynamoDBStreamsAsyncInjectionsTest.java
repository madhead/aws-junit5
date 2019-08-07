package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsAsync;
import com.amazonaws.services.dynamodbv2.model.ListStreamsRequest;
import com.amazonaws.services.dynamodbv2.model.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDBLocalExtension.class)
class AmazonDynamoDBStreamsAsyncInjectionsTest {
    @DynamoDBLocal(
        serviceConfiguration = SystemPropertiesDynamoDBStreamsServiceConfiguration.class
    )
    private AmazonDynamoDBStreamsAsync amazonDynamoDBStreamsAsync;

    @Test
    @DisplayName("AmazonDynamoDBStreamsAsync should be injected and configured properly with system properties")
    void test() throws Exception {
        Assertions.assertNotNull(amazonDynamoDBStreamsAsync);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            amazonDynamoDBStreamsAsync
                .listStreamsAsync(new ListStreamsRequest().withTableName("table"))
                .get()
                .getStreams()
                .stream()
                .map(Stream::getTableName)
                .sorted()
                .collect(Collectors.toList())
        );
    }
}
