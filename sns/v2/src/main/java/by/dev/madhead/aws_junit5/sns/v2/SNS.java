package by.dev.madhead.aws_junit5.sns.v2;

import by.dev.madhead.aws_junit5.common.impl.AWSClientExtension;
import by.dev.madhead.aws_junit5.common.v2.AWSClientFactory;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.SnsClient;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link SNS} to extend tests with fields that are subjects for SNS injection.
 */
public class SNS extends AWSClientExtension {
    private final static Map<Class, AWSClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(SnsClient.class, new AWSClientFactory<>(SnsClient.builder()));
        factories.put(SnsAsyncClient.class, new AWSClientFactory<>(SnsAsyncClient.builder()));
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
