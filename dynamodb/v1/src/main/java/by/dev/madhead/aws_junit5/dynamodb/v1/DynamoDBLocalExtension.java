package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.dynamodb.v1.impl.*;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreams;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsAsync;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Use {@code DynamoDBLocalExtension} to extend tests with fields that are subjects for DynamoDB injection.
 */
public class DynamoDBLocalExtension implements TestInstancePostProcessor {
    private final static Map<Class, DynamoDBClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(AmazonDynamoDB.class, new AmazonDynamoDBFactory());
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
