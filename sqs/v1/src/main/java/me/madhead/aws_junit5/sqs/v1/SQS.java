package me.madhead.aws_junit5.sqs.v1;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import me.madhead.aws_junit5.common.impl.AWSClientExtensionBase;
import me.madhead.aws_junit5.common.v1.AWSClientFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link SQS} to extend tests with fields that are subjects for SQS injection.
 */
public class SQS extends AWSClientExtensionBase {
    private final static Map<Class<?>, AWSClientFactory<?, ?>> factories;

    static {
        factories = new HashMap<>();
        factories.put(AmazonSQS.class, new AWSClientFactory<>(AmazonSQSClientBuilder.standard()));
        factories.put(AmazonSQSAsync.class, new AWSClientFactory<>(AmazonSQSAsyncClientBuilder.standard()));
    }

    @Override
    protected Map<Class<?>, AWSClientFactory<?, ?>> factories() {
        return factories;
    }
}
