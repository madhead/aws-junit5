package by.dev.madhead.aws_junit5.dynamodb.v2.impl;

import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClientBuilder;

public class DynamoDbAsyncFactory extends AbstractDynamoDbClientFactory<DynamoDbAsyncClientBuilder, DynamoDbAsyncClient> {
    public DynamoDbAsyncFactory() {
        super(DynamoDbAsyncClient.builder());
    }
}
