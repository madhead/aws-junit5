package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreams;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsClientBuilder;

public class AmazonDynamoDBStreamsFactory extends AbstractDynamoDBClientFactory<AmazonDynamoDBStreamsClientBuilder, AmazonDynamoDBStreams> {
    public AmazonDynamoDBStreamsFactory() {
        super(AmazonDynamoDBStreamsClientBuilder.standard());
    }
}
