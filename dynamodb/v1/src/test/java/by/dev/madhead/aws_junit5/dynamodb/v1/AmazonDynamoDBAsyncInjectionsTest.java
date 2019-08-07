package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDBLocalExtension.class)
class AmazonDynamoDBAsyncInjectionsTest {
    @DynamoDBLocal(
        serviceConfiguration = SystemPropertiesDynamoDBServiceConfiguration.class
    )
    private AmazonDynamoDBAsync amazonDynamoDBAsync;

    @Test
    @DisplayName("AmazonDynamoDBAsync should be injected and configured properly with system properties")
    void test() throws Exception {
        Assertions.assertNotNull(amazonDynamoDBAsync);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            amazonDynamoDBAsync.listTablesAsync().get().getTableNames().stream().sorted().collect(Collectors.toList())
        );
    }
}
