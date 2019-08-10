package by.dev.madhead.aws_junit5.sqs.v1;

import by.dev.madhead.aws_junit5.common.impl.AWSClientExtension;
import by.dev.madhead.aws_junit5.common.v1.AWSClientFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link SQS} to extend tests with fields that are subjects for SQS injection.
 */
public class SQS extends AWSClientExtension {
    private final static Map<Class, AWSClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(AmazonSQS.class, new AWSClientFactory<>(AmazonSQSClientBuilder.standard()));
        factories.put(AmazonSQSAsync.class, new AWSClientFactory<>(AmazonSQSAsyncClientBuilder.standard()));
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
