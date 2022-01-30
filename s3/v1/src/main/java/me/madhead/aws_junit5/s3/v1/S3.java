package me.madhead.aws_junit5.s3.v1;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import me.madhead.aws_junit5.common.impl.AWSClientExtensionBase;
import me.madhead.aws_junit5.common.v1.AWSClientFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link S3} to extend tests with fields that are subjects for S3 injection.
 */
public class S3 extends AWSClientExtensionBase {
    private final static Map<Class<?>, AWSClientFactory<?, ?>> factories;

    static {
        factories = new HashMap<>();
        factories.put(AmazonS3.class, new AWSClientFactory<>(AmazonS3ClientBuilder.standard()));
    }

    @Override
    protected Map<Class<?>, AWSClientFactory<?, ?>> factories() {
        return factories;
    }
}
