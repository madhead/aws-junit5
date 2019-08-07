package by.dev.madhead.aws_junit5.dynamodb.v2.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

class DynamoDbAsyncFactoryTest {
    private final DynamoDbAsyncFactory dynamoDbAsyncFactory = new DynamoDbAsyncFactory();

    @Test
    void test() throws Exception {
        final DynamoDbAsyncClient client = dynamoDbAsyncFactory.createClient(
            new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
        );

        Assertions.assertNotNull(client);
    }
}
