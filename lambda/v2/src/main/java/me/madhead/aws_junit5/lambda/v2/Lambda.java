package me.madhead.aws_junit5.lambda.v2;

import me.madhead.aws_junit5.common.impl.AWSClientExtensionBase;
import me.madhead.aws_junit5.common.v2.AWSClientFactory;
import software.amazon.awssdk.services.lambda.LambdaAsyncClient;
import software.amazon.awssdk.services.lambda.LambdaClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link Lambda} to extend tests with fields that are subjects for AWS Lambda injection.
 */
public class Lambda extends AWSClientExtensionBase {
    private final static Map<Class<?>, AWSClientFactory<?, ?>> factories;

    static {
        factories = new HashMap<>();
        factories.put(LambdaClient.class, new AWSClientFactory<>(LambdaClient.builder()));
        factories.put(LambdaAsyncClient.class, new AWSClientFactory<>(LambdaAsyncClient.builder()));
    }

    @Override
    protected Map<Class<?>, AWSClientFactory<?, ?>> factories() {
        return factories;
    }
}
