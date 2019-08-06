package by.dev.madhead.aws_junit5.dynamodb.v1;

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
        serviceConfiguration = InjectionsTestSystemPropertiesServiceConfigurationInstance1.class
    )
    private AmazonDynamoDBStreamsAsync amazonDynamoDBStreamsAsyncBySystemProperty;

    @DynamoDBLocal(
        serviceConfiguration = InjectionsTestEnvironmentVariablesServiceConfigurationInstance2.class
    )
    private AmazonDynamoDBStreamsAsync amazonDynamoDBStreamsAsyncByEnvironmentVariable;

    @Test
    @DisplayName("AmazonDynamoDBStreamsAsync should be injected and configured properly with system properties")
    void test1() throws Exception {
        Assertions.assertNotNull(amazonDynamoDBStreamsAsyncBySystemProperty);

        Assertions.assertEquals(
            Collections.singletonList("table_1"),
            amazonDynamoDBStreamsAsyncBySystemProperty
                .listStreamsAsync(new ListStreamsRequest().withTableName("table_1"))
                .get()
                .getStreams()
                .stream()
                .map(Stream::getTableName)
                .sorted()
                .collect(Collectors.toList())
        );
    }

    @Test
    @DisplayName("AmazonDynamoDBStreamsAsync should be injected and configured properly with environment variables")
    void test2() throws Exception {
        Assertions.assertNotNull(amazonDynamoDBStreamsAsyncByEnvironmentVariable);

        Assertions.assertEquals(
            Collections.singletonList("table_2"),
            amazonDynamoDBStreamsAsyncByEnvironmentVariable
                .listStreamsAsync(new ListStreamsRequest().withTableName("table_2"))
                .get()
                .getStreams()
                .stream()
                .map(Stream::getTableName)
                .sorted()
                .collect(Collectors.toList())
        );
    }
}
