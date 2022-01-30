package me.madhead.aws_junit5.ses.v1;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import me.madhead.aws_junit5.common.impl.AWSClientExtensionBase;
import me.madhead.aws_junit5.common.v1.AWSClientFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link SES} to extend tests with fields that are subjects for SES injection.
 */
public class SES extends AWSClientExtensionBase {
    private final static Map<Class<?>, AWSClientFactory<?, ?>> factories;

    static {
        factories = new HashMap<>();
        factories.put(AmazonSimpleEmailService.class, new AWSClientFactory<>(AmazonSimpleEmailServiceClientBuilder.standard()));
        factories.put(AmazonSimpleEmailServiceAsync.class, new AWSClientFactory<>(AmazonSimpleEmailServiceAsyncClientBuilder.standard()));
    }

    @Override
    protected Map<Class<?>, AWSClientFactory<?, ?>> factories() {
        return factories;
    }
}
