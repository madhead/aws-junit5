package me.madhead.aws_junit5.kinesis.v1;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisAsync;
import com.amazonaws.services.kinesis.AmazonKinesisAsyncClientBuilder;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseAsync;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseAsyncClientBuilder;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import me.madhead.aws_junit5.common.impl.AWSClientExtensionBase;
import me.madhead.aws_junit5.common.v1.AWSClientFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Use {@link Kinesis} to extend tests with fields that are subjects for Kinesis injection.
 */
public class Kinesis extends AWSClientExtensionBase {
    private final static Map<Class<?>, AWSClientFactory<?, ?>> factories;

    static {
        factories = new HashMap<>();
        factories.put(AmazonKinesis.class, new AWSClientFactory<>(AmazonKinesisClientBuilder.standard()));
        factories.put(AmazonKinesisAsync.class, new AWSClientFactory<>(AmazonKinesisAsyncClientBuilder.standard()));
        factories.put(AmazonKinesisFirehose.class, new AWSClientFactory<>(AmazonKinesisFirehoseClientBuilder.standard()));
        factories.put(AmazonKinesisFirehoseAsync.class, new AWSClientFactory<>(AmazonKinesisFirehoseAsyncClientBuilder.standard()));
    }

    @Override
    protected Map<Class<?>, AWSClientFactory<?, ?>> factories() {
        return factories;
    }
}
