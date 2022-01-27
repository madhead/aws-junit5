package me.madhead.aws_junit5.dynamo.v2;

import me.madhead.aws_junit5.common.impl.AWSClientExtensionBase;
import me.madhead.aws_junit5.common.v2.AWSClientFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link DynamoDB} to extend tests with fields that are subjects for DynamoDB injection.
 */
public class DynamoDB extends AWSClientExtensionBase {
    private final static Map<Class<?>, AWSClientFactory<?, ?>> factories;

    static {
        factories = new HashMap<>();
        factories.put(DynamoDbClient.class, new AWSClientFactory<>(DynamoDbClient.builder()));
        factories.put(DynamoDbAsyncClient.class, new AWSClientFactory<>(DynamoDbAsyncClient.builder()));
        factories.put(DynamoDbStreamsClient.class, new AWSClientFactory<>(DynamoDbStreamsClient.builder()));
        factories.put(DynamoDbStreamsAsyncClient.class, new AWSClientFactory<>(DynamoDbStreamsAsyncClient.builder()));
    }

    @Override
    protected Map<Class<?>, AWSClientFactory<?, ?>> factories() {
        return factories;
    }
}
