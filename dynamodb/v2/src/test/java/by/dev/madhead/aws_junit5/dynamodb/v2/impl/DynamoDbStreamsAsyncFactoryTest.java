package by.dev.madhead.aws_junit5.dynamodb.v2.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient;

class DynamoDbStreamsAsyncFactoryTest {
    private final DynamoDbStreamsAsyncFactory dynamoDbAsyncFactory = new DynamoDbStreamsAsyncFactory();

    @Test
    void test() throws Exception {
        final DynamoDbStreamsAsyncClient client = dynamoDbAsyncFactory.createClient(
            new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
        );

        Assertions.assertNotNull(client);
    }
}
