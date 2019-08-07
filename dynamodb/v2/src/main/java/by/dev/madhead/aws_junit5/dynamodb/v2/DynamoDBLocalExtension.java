package by.dev.madhead.aws_junit5.dynamodb.v2;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import by.dev.madhead.aws_junit5.dynamodb.v2.impl.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
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
public class DynamoDBLocalExtension implements TestInstancePostProcessor {
    private final static Map<Class, DynamoDbClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(DynamoDbClient.class, new DynamoDbFactory());
        factories.put(DynamoDbAsyncClient.class, new DynamoDbAsyncFactory());
        factories.put(DynamoDbStreamsClient.class, new DynamoDbStreamsFactory());
        factories.put(DynamoDbStreamsAsyncClient.class, new DynamoDbStreamsAsyncFactory());
    }

    @Override
    public void postProcessTestInstance(final Object testInstance, final ExtensionContext context) throws Exception {
        for (final Field field : testInstance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(DynamoDBLocal.class)) {
                inject(testInstance, field);
            }
        }
    }

    private void inject(final Object testInstance, final Field field) throws Exception {
        final DynamoDBLocal configuration = field.getAnnotation(DynamoDBLocal.class);
        final boolean accessible = field.isAccessible();

        try {
            field.setAccessible(true);
            if (factories.containsKey(field.getType())) {
                field.set(testInstance, factories.get(field.getType()).createClient(configuration));
            } else {
                throw new IllegalArgumentException(field.getType() + " is not supported by DynamoDBLocalExtension.");
            }
        } finally {
            field.setAccessible(accessible);
        }
    }
}
