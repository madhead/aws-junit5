package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmazonDynamoDBStreamsAsyncFactoryTest {
    private final AmazonDynamoDBStreamsAsyncFactory amazonDynamoDBStreamsAsyncFactory =
        new AmazonDynamoDBStreamsAsyncFactory();

    @Test
    @DisplayName("AmazonDynamoDBStreamsAsyncFactory should create instances of AmazonDynamoDBStreamsAsync")
    void test() throws Exception {
        Assertions.assertNotNull(
            amazonDynamoDBStreamsAsyncFactory.createClient(
                new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
            )
        );
    }
}
