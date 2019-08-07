package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AmazonDynamoDBAsyncFactoryTest {
    private final AmazonDynamoDBAsyncFactory amazonDynamoDBAsyncFactory = new AmazonDynamoDBAsyncFactory();

    @Test
    void test() throws Exception {
        final AmazonDynamoDBAsync client = amazonDynamoDBAsyncFactory.createClient(
            new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
        );

        Assertions.assertNotNull(client);
    }
}
