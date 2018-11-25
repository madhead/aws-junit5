package by.dev.madhead.aws_junit5.dynamodb.v1;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DynamoDBLocalExtension.class)
class SingleAmazonDynamoDBFieldInjectionByEnvironmentVariableTest {
    @DynamoDBLocal(
        urlEnvironmentVariable = "BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL1"
    )
    private AmazonDynamoDB amazonDynamoDB;

    @Test
    @DisplayName("Single field of AmazonDynamoDB type should be injected and configured by environment variable")
    void test() {
        Assertions.assertNotNull(amazonDynamoDB);

        amazonDynamoDB.listTables();
    }
}
