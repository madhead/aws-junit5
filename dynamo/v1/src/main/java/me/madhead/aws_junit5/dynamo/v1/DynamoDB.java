package me.madhead.aws_junit5.dynamo.v1;

import com.amazonaws.services.dynamodbv2.*;
import me.madhead.aws_junit5.common.impl.AWSClientExtensionBase;
import me.madhead.aws_junit5.common.v1.AWSClientFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link DynamoDB} to extend tests with fields that are subjects for DynamoDB injection.
 */
public class DynamoDB extends AWSClientExtensionBase {
    private final static Map<Class<?>, AWSClientFactory<?, ?>> factories;

    static {
        factories = new HashMap<>();
        factories.put(AmazonDynamoDB.class, new AWSClientFactory<>(AmazonDynamoDBClientBuilder.standard()));
        factories.put(AmazonDynamoDBAsync.class, new AWSClientFactory<>(AmazonDynamoDBAsyncClientBuilder.standard()));
        factories.put(AmazonDynamoDBStreams.class, new AWSClientFactory<>(AmazonDynamoDBStreamsClientBuilder.standard()));
        factories.put(AmazonDynamoDBStreamsAsync.class, new AWSClientFactory<>(AmazonDynamoDBStreamsAsyncClientBuilder.standard()));
    }

    @Override
    protected Map<Class<?>, AWSClientFactory<?, ?>> factories() {
        return factories;
    }
}
