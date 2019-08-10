package by.dev.madhead.aws_junit5.dynamodb.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.impl.AWSClientExtension;
import by.dev.madhead.aws_junit5.common.v2.AWSClientFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Use {@code DynamoDBLocalExtension} to extend tests with fields that are subjects for DynamoDB injection.
 */
public class DynamoDB extends AWSClientExtension {
    private final static Map<Class, AWSClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(DynamoDbClient.class, new AWSClientFactory<>(DynamoDbClient.builder()));
        factories.put(DynamoDbAsyncClient.class, new AWSClientFactory<>(DynamoDbAsyncClient.builder()));
        factories.put(DynamoDbStreamsClient.class, new AWSClientFactory<>(DynamoDbStreamsClient.builder()));
        factories.put(DynamoDbStreamsAsyncClient.class, new AWSClientFactory<>(DynamoDbStreamsAsyncClient.builder()));
    }

    @Override
    protected boolean supports(final Field field) {
        return factories.containsKey(field.getType());
    }

    @Override
    protected Object client(final Field field, AWSClient configuration) throws Exception {
        return factories.get(field.getType()).createClient(configuration);
    }
}
