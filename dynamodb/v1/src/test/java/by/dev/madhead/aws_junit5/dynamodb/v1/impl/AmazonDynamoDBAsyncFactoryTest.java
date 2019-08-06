package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmazonDynamoDBAsyncFactoryTest {
    private final AmazonDynamoDBAsyncFactory amazonDynamoDBAsyncFactory = new AmazonDynamoDBAsyncFactory();

    @Test
    @DisplayName("AmazonDynamoDBAsyncFactory should create instances of AmazonDynamoDBAsync")
    void test() throws Exception {
        Assertions.assertNotNull(
            amazonDynamoDBAsyncFactory.createClient(
                new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
            )
        );
    }
}
