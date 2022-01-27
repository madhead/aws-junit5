package me.madhead.aws_junit5.lambda.v1;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaAsync;
import com.amazonaws.services.lambda.AWSLambdaAsyncClientBuilder;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import me.madhead.aws_junit5.common.impl.AWSClientExtension;
import me.madhead.aws_junit5.common.v1.AWSClientFactory;

/**
 * Use {@link Lambda} to extend tests with fields that are subjects for AWS Lambda injection.
 */
public class Lambda extends AWSClientExtension {
    private final static Map<Class, AWSClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(AWSLambda.class, new AWSClientFactory<>(AWSLambdaClientBuilder.standard()));
        factories.put(AWSLambdaAsync.class, new AWSClientFactory<>(AWSLambdaAsyncClientBuilder.standard()));
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
