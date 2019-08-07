package by.dev.madhead.aws_junit5.dynamodb.v2.impl;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

public class DynamoDbFactory extends AbstractDynamoDbClientFactory<DynamoDbClientBuilder, DynamoDbClient> {
    public DynamoDbFactory() {
        super(DynamoDbClient.builder());
    }
}
