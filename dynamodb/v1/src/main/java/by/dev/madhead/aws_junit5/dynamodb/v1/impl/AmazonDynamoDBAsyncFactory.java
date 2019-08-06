package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;

public class AmazonDynamoDBAsyncFactory extends AbstractDynamoDBClientFactory<AmazonDynamoDBAsyncClientBuilder, AmazonDynamoDBAsync> {
    public AmazonDynamoDBAsyncFactory() {
        super(AmazonDynamoDBAsyncClientBuilder.standard());
    }
}
