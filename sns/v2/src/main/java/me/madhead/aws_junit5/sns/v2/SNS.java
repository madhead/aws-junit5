package me.madhead.aws_junit5.sns.v2;

import me.madhead.aws_junit5.common.impl.AWSClientExtensionBase;
import me.madhead.aws_junit5.common.v2.AWSClientFactory;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.SnsClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link SNS} to extend tests with fields that are subjects for SNS injection.
 */
public class SNS extends AWSClientExtensionBase {
    private final static Map<Class<?>, AWSClientFactory<?, ?>> factories;

    static {
        factories = new HashMap<>();
        factories.put(SnsClient.class, new AWSClientFactory<>(SnsClient.builder()));
        factories.put(SnsAsyncClient.class, new AWSClientFactory<>(SnsAsyncClient.builder()));
    }

    @Override
    protected Map<Class<?>, AWSClientFactory<?, ?>> factories() {
        return factories;
    }
}
