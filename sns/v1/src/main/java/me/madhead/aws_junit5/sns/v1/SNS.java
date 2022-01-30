package me.madhead.aws_junit5.sns.v1;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import me.madhead.aws_junit5.common.impl.AWSClientExtensionBase;
import me.madhead.aws_junit5.common.v1.AWSClientFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link SNS} to extend tests with fields that are subjects for SNS injection.
 */
public class SNS extends AWSClientExtensionBase {
    private final static Map<Class<?>, AWSClientFactory<?, ?>> factories;

    static {
        factories = new HashMap<>();
        factories.put(AmazonSNS.class, new AWSClientFactory<>(AmazonSNSClientBuilder.standard()));
        factories.put(AmazonSNSAsync.class, new AWSClientFactory<>(AmazonSNSAsyncClientBuilder.standard()));
    }

    @Override
    protected Map<Class<?>, AWSClientFactory<?, ?>> factories() {
        return factories;
    }
}
