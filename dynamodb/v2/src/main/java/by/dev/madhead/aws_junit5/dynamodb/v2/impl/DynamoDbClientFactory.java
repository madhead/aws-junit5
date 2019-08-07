package by.dev.madhead.aws_junit5.dynamodb.v2.impl;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;

public interface DynamoDbClientFactory<T> {
    T createClient(final DynamoDBLocal configuration) throws Exception;
}
