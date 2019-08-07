package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AmazonDynamoDBFactoryTest {
    private final AmazonDynamoDBFactory amazonDynamoDBFactory = new AmazonDynamoDBFactory();

    @Test
    void test() throws Exception {
        final AmazonDynamoDB client = amazonDynamoDBFactory.createClient(
            new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
        );

        Assertions.assertNotNull(client);
    }
}
