package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDBLocalExtension.class)
class AmazonDynamoDBInjectionsTest {
    @DynamoDBLocal(
        serviceConfiguration = EnvironmentVariablesDynamoDBServiceConfiguration.class
    )
    private AmazonDynamoDB amazonDynamoDB;

    @Test
    @DisplayName("AmazonDynamoDB should be injected and configured properly with environment variables")
    void test() throws Exception {
        Assertions.assertNotNull(amazonDynamoDB);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            amazonDynamoDB.listTables().getTableNames().stream().sorted().collect(Collectors.toList())
        );
    }
}
