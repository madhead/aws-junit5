package me.madhead.aws_junit5.ses.v2;

import me.madhead.aws_junit5.common.impl.AWSClientExtension;
import me.madhead.aws_junit5.common.v2.AWSClientFactory;
import software.amazon.awssdk.services.ses.SesAsyncClient;
import software.amazon.awssdk.services.ses.SesClient;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link SES} to extend tests with fields that are subjects for SES injection.
 */
public class SES extends AWSClientExtension {
    private final static Map<Class, AWSClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(SesClient.class, new AWSClientFactory<>(SesClient.builder()));
        factories.put(SesAsyncClient.class, new AWSClientFactory<>(SesAsyncClient.builder()));
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
