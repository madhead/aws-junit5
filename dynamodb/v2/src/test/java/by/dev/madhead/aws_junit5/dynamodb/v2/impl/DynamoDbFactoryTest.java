package by.dev.madhead.aws_junit5.dynamodb.v2.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

class DynamoDbFactoryTest {
    private final DynamoDbFactory dynamoDBFactory = new DynamoDbFactory();

    @Test
    void test() throws Exception {
        final DynamoDbClient client = dynamoDBFactory.createClient(
            new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
        );

        Assertions.assertNotNull(client);
    }
}
