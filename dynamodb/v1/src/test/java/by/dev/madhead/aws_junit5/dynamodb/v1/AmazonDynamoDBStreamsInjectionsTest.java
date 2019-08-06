package by.dev.madhead.aws_junit5.dynamodb.v1;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreams;
import com.amazonaws.services.dynamodbv2.model.ListStreamsRequest;
import com.amazonaws.services.dynamodbv2.model.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDBLocalExtension.class)
class AmazonDynamoDBStreamsInjectionsTest {
    @DynamoDBLocal(
        serviceConfiguration = InjectionsTestEnvironmentVariablesServiceConfigurationInstance1.class
    )
    private AmazonDynamoDBStreams amazonDynamoDBStreamsBySystemProperty;

    @DynamoDBLocal(
        serviceConfiguration = InjectionsTestSystemPropertiesServiceConfigurationInstance2.class
    )
    private AmazonDynamoDBStreams amazonDynamoDBStreamsByEnvironmentVariable;

    @Test
    @DisplayName("AmazonDynamoDBStreams should be injected and configured properly with environment variables")
    void test1() {
        Assertions.assertNotNull(amazonDynamoDBStreamsBySystemProperty);

        Assertions.assertEquals(
            Collections.singletonList("table_1"),
            amazonDynamoDBStreamsBySystemProperty
                .listStreams(new ListStreamsRequest().withTableName("table_1"))
                .getStreams()
                .stream()
                .map(Stream::getTableName)
                .sorted()
                .collect(Collectors.toList())
        );
    }

    @Test
    @DisplayName("AmazonDynamoDBStreams should be injected and configured properly with system properties")
    void test2() {
        Assertions.assertNotNull(amazonDynamoDBStreamsByEnvironmentVariable);

        Assertions.assertEquals(
            Collections.singletonList("table_2"),
            amazonDynamoDBStreamsByEnvironmentVariable
                .listStreams(new ListStreamsRequest().withTableName("table_2"))
                .getStreams()
                .stream()
                .map(Stream::getTableName)
                .sorted()
                .collect(Collectors.toList())
        );
    }
}
