package me.madhead.aws_junit5.lambda.v2;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import me.madhead.aws_junit5.common.impl.AWSClientExtension;
import me.madhead.aws_junit5.common.v2.AWSClientFactory;
import software.amazon.awssdk.services.lambda.LambdaAsyncClient;
import software.amazon.awssdk.services.lambda.LambdaClient;

/**
 * Use {@link Lambda} to extend tests with fields that are subjects for AWS Lambda injection.
 */
public class Lambda extends AWSClientExtension {
    private final static Map<Class, AWSClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(LambdaClient.class, new AWSClientFactory<>(LambdaClient.builder()));
        factories.put(LambdaAsyncClient.class, new AWSClientFactory<>(LambdaAsyncClient.builder()));
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
