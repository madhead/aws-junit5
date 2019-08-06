package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmazonDynamoDBStreamsFactoryTest {
    private final AmazonDynamoDBStreamsFactory amazonDynamoDBStreamsFactory = new AmazonDynamoDBStreamsFactory();

    @Test
    @DisplayName("AmazonDynamoDBStreamsFactory should create instances of AmazonDynamoDBStreams")
    void test() throws Exception {
        Assertions.assertNotNull(
            amazonDynamoDBStreamsFactory.createClient(
                new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
            )
        );
    }
}
