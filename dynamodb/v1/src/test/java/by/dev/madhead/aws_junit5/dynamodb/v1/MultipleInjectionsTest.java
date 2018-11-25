package by.dev.madhead.aws_junit5.dynamodb.v1;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DynamoDBLocalExtension.class)
class MultipleInjectionsTest {
    @DynamoDBLocal(
        urlSystemProperty = "by.dev.madhead.aws_junit5.dynamodb.v1.url1"
    )
    private AmazonDynamoDB amazonDynamoDBBySystemProperty;

    @DynamoDBLocal(
        urlEnvironmentVariable = "BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL1"
    )
    private AmazonDynamoDB amazonDynamoDBByEnvironmentVariable;

    @Test
    @DisplayName("Multiple fields should be injected and configured properly")
    void test() {
        Assertions.assertNotNull(amazonDynamoDBBySystemProperty);
        Assertions.assertNotNull(amazonDynamoDBByEnvironmentVariable);

        amazonDynamoDBBySystemProperty.listTables();
        amazonDynamoDBByEnvironmentVariable.listTables();
    }
}
