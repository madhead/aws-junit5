package me.madhead.aws_junit5.sqs.v2;

import me.madhead.aws_junit5.common.impl.AWSClientExtensionBase;
import me.madhead.aws_junit5.common.v2.AWSClientFactory;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link SQS} to extend tests with fields that are subjects for SQS injection.
 */
public class SQS extends AWSClientExtensionBase {
    private final static Map<Class<?>, AWSClientFactory<?, ?>> factories;

    static {
        factories = new HashMap<>();
        factories.put(SqsClient.class, new AWSClientFactory<>(SqsClient.builder()));
        factories.put(SqsAsyncClient.class, new AWSClientFactory<>(SqsAsyncClient.builder()));
    }

    @Override
    protected Map<Class<?>, AWSClientFactory<?, ?>> factories() {
        return factories;
    }
}
