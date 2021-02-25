package me.madhead.aws_junit5.kinesis.v2;

import me.madhead.aws_junit5.common.impl.AWSClientExtension;
import me.madhead.aws_junit5.common.v2.AWSClientFactory;
import software.amazon.awssdk.services.firehose.FirehoseAsyncClient;
import software.amazon.awssdk.services.firehose.FirehoseClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisClient;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link Kinesis} to extend tests with fields that are subjects for Kinesis injection.
 */
public class Kinesis extends AWSClientExtension {
    private final static Map<Class, AWSClientFactory> factories;

    static {
        factories = new HashMap<>();
        factories.put(KinesisClient.class, new AWSClientFactory<>(KinesisClient.builder()));
        factories.put(KinesisAsyncClient.class, new AWSClientFactory<>(KinesisAsyncClient.builder()));
        factories.put(FirehoseClient.class, new AWSClientFactory<>(FirehoseClient.builder()));
        factories.put(FirehoseAsyncClient.class, new AWSClientFactory<>(FirehoseAsyncClient.builder()));
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
