package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AmazonDynamoDBStreamsFactoryTest {
    private final AmazonDynamoDBStreamsFactory amazonDynamoDBStreamsFactory = new AmazonDynamoDBStreamsFactory();

    @Test
    void test() throws Exception {
        final AmazonDynamoDBStreams client = amazonDynamoDBStreamsFactory.createClient(
            new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
        );

        Assertions.assertNotNull(client);
    }
}
