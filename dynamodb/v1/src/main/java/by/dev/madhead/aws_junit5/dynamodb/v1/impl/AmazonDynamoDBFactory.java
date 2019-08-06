package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class AmazonDynamoDBFactory extends AbstractDynamoDBClientFactory<AmazonDynamoDBClientBuilder, AmazonDynamoDB> {
    public AmazonDynamoDBFactory() {
        super(AmazonDynamoDBClientBuilder.standard());
    }
}
