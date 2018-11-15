package by.dev.madhead.aws_junit5.dynamodb.v1;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DynamoDBLocalExtension.class)
@DisabledIfEnvironmentVariable(named = "CI", matches = ".+")
class SingleAmazonDynamoDBFieldInjectionByURLTest {
    @DynamoDBLocal(url = "http://localhost:8000")
    private AmazonDynamoDB amazonDynamoDB;

    @Test
    @DisplayName("Single field of AmazonDynamoDB type should be injected and configured by raw URL")
    void test() {
        Assertions.assertNotNull(amazonDynamoDB);

        amazonDynamoDB.listTables();
    }
}
