package me.madhead.aws_junit5.s3.v2;

import me.madhead.aws_junit5.common.impl.AWSClientExtension;
import me.madhead.aws_junit5.common.v2.AWSClientFactory;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link S3} to extend tests with fields that are subjects for S3 injection.
 */
public class S3 extends AWSClientExtension {
    private final static Map<Class, AWSClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(S3Client.class, new AWSClientFactory<>(S3Client.builder()));
        factories.put(S3AsyncClient.class, new AWSClientFactory<>(S3AsyncClient.builder()));
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
