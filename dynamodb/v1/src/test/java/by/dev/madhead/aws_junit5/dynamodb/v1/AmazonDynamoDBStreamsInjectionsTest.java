package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
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
        serviceConfiguration = EnvironmentVariablesDynamoDBServiceConfiguration.class
    )
    private AmazonDynamoDBStreams amazonDynamoDBStreams;

    @Test
    @DisplayName("AmazonDynamoDBStreams should be injected and configured properly with environment variables")
    void test() {
        Assertions.assertNotNull(amazonDynamoDBStreams);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            amazonDynamoDBStreams
                .listStreams(new ListStreamsRequest().withTableName("table"))
                .getStreams()
                .stream()
                .map(Stream::getTableName)
                .sorted()
                .collect(Collectors.toList())
        );
    }
}
