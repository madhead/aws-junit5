package by.dev.madhead.aws_junit5.dynamodb.v2.impl;

import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClientBuilder;

public class DynamoDbStreamsAsyncFactory
    extends AbstractDynamoDbClientFactory<DynamoDbStreamsAsyncClientBuilder, DynamoDbStreamsAsyncClient> {
    public DynamoDbStreamsAsyncFactory() {
        super(DynamoDbStreamsAsyncClient.builder());
    }
}
