package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsAsyncClientBuilder;

public class AmazonDynamoDBStreamsAsyncFactory
    extends AbstractDynamoDBClientFactory<AmazonDynamoDBStreamsAsyncClientBuilder, AmazonDynamoDBStreamsAsync> {
    public AmazonDynamoDBStreamsAsyncFactory() {
        super(AmazonDynamoDBStreamsAsyncClientBuilder.standard());
    }
}
