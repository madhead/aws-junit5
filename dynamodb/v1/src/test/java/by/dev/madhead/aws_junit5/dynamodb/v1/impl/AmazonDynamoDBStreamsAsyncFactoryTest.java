package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsAsync;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AmazonDynamoDBStreamsAsyncFactoryTest {
    private final AmazonDynamoDBStreamsAsyncFactory amazonDynamoDBStreamsAsyncFactory =
        new AmazonDynamoDBStreamsAsyncFactory();

    @Test
    void test() throws Exception {
        final AmazonDynamoDBStreamsAsync client = amazonDynamoDBStreamsAsyncFactory.createClient(
            new DynamoDBLocalImpl(FactoryTestServiceConfiguration.class)
        );
        Assertions.assertNotNull(client);
    }
}
