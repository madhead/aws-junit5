package by.dev.madhead.aws_junit5.dynamodb.v2.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient;

class DynamoDbStreamsFactoryTest {
    private final DynamoDbStreamsFactory dynamoDbAsyncFactory = new DynamoDbStreamsFactory();

    @Test
    void test() throws Exception {
        final DynamoDbStreamsClient client = dynamoDbAsyncFactory.createClient(
            new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
        );

        Assertions.assertNotNull(client);
    }
}
