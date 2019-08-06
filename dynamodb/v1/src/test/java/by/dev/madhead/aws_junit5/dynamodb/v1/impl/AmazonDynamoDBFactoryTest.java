package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmazonDynamoDBFactoryTest {
    private final AmazonDynamoDBFactory amazonDynamoDBFactory = new AmazonDynamoDBFactory();

    @Test
    @DisplayName("AmazonDynamoDBFactory should create instances of AmazonDynamoDB")
    void test() throws Exception {
        Assertions.assertNotNull(
            amazonDynamoDBFactory.createClient(
                new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
            )
        );
    }
}
