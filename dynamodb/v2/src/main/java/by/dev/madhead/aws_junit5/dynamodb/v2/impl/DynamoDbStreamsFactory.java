package by.dev.madhead.aws_junit5.dynamodb.v2.impl;

import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClientBuilder;

public class DynamoDbStreamsFactory extends AbstractDynamoDbClientFactory<DynamoDbStreamsClientBuilder, DynamoDbStreamsClient> {
    public DynamoDbStreamsFactory() {
        super(DynamoDbStreamsClient.builder());
    }
}
