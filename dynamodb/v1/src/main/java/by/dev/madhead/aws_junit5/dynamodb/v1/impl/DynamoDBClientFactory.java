package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import by.dev.madhead.aws_junit5.dynamodb.v1.DynamoDBLocal;

public interface DynamoDBClientFactory<T> {
    T createClient(final DynamoDBLocal configuration) throws Exception;
}
