package by.dev.madhead.aws_junit5.s3.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.impl.AWSClientExtension;
import by.dev.madhead.aws_junit5.common.v1.AWSClientFactory;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Use {@code S3} to extend tests with fields that are subjects for S3 injection.
 */
public class S3 extends AWSClientExtension {
    private final static Map<Class, AWSClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(AmazonS3.class, new AWSClientFactory<>(AmazonS3ClientBuilder.standard()));
    }

    @Override
    protected boolean supports(final Field field) {
        return factories.containsKey(field.getType());
    }

    @Override
    protected Object client(final Field field, AWSClient configuration) throws Exception {
        return factories.get(field.getType()).createClient(configuration);
    }
}
