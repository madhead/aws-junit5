package by.dev.madhead.aws_junit5.dynamo.v1;

import by.dev.madhead.aws_junit5.common.impl.AWSClientExtension;
import by.dev.madhead.aws_junit5.common.v1.AWSClientFactory;
import com.amazonaws.services.dynamodbv2.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link DynamoDB} to extend tests with fields that are subjects for DynamoDB injection.
 */
public class DynamoDB extends AWSClientExtension {
    private final static Map<Class, AWSClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(AmazonDynamoDB.class, new AWSClientFactory<>(AmazonDynamoDBClientBuilder.standard()));
        factories.put(AmazonDynamoDBAsync.class, new AWSClientFactory<>(AmazonDynamoDBAsyncClientBuilder.standard()));
        factories.put(AmazonDynamoDBStreams.class, new AWSClientFactory<>(AmazonDynamoDBStreamsClientBuilder.standard()));
        factories.put(AmazonDynamoDBStreamsAsync.class, new AWSClientFactory<>(AmazonDynamoDBStreamsAsyncClientBuilder.standard()));
    }

    @Override
    protected boolean supports(final Field field) {
        return factories.containsKey(field.getType());
    }

    @Override
    protected Object client(final Field field) throws Exception {
        return factories.get(field.getType()).client(field);
    }
}
