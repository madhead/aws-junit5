package me.madhead.aws_junit5.ses.v1;

import me.madhead.aws_junit5.common.impl.AWSClientExtension;
import me.madhead.aws_junit5.common.v1.AWSClientFactory;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

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
        factories.put(AmazonSimpleEmailService.class, new AWSClientFactory<>(AmazonSimpleEmailServiceClientBuilder.standard()));
        factories.put(AmazonSimpleEmailServiceAsync.class, new AWSClientFactory<>(AmazonSimpleEmailServiceAsyncClientBuilder.standard()));
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
