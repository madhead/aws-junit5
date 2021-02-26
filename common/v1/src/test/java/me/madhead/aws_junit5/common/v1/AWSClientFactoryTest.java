package me.madhead.aws_junit5.common.v1;

import me.madhead.aws_junit5.common.AWSClient;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisAsync;
import com.amazonaws.services.kinesis.AmazonKinesisAsyncClientBuilder;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseAsync;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseAsyncClientBuilder;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.HashMap;
import java.util.stream.Stream;

class AWSClientFactoryTest {
    @SuppressWarnings("unused")
    @AWSClient(endpoint = Endpoint.class)
    private Object field;

    @SuppressWarnings("unused")
    @AWSClient(endpoint = Endpoint.class)
    @AWSAdvancedConfiguration
    private Object deFault;

    @SuppressWarnings("unused")
    @AWSClient(endpoint = Endpoint.class)
    @AWSAdvancedConfiguration(
        clientConfigurationFactory = CustomClientConfigurationFactory.class
    )
    private Object custom;

    @TestFactory
    Stream<DynamicTest> test() {
        return
            new HashMap<Class, AwsClientBuilder>() {{
                put(AmazonDynamoDB.class, AmazonDynamoDBClientBuilder.standard());
                put(AmazonDynamoDBAsync.class, AmazonDynamoDBAsyncClientBuilder.standard());
                put(AmazonDynamoDBStreams.class, AmazonDynamoDBStreamsClientBuilder.standard());
                put(AmazonDynamoDBStreamsAsync.class, AmazonDynamoDBStreamsAsyncClientBuilder.standard());
                put(AmazonS3.class, AmazonS3ClientBuilder.standard());
                put(AmazonKinesis.class, AmazonKinesisClientBuilder.standard());
                put(AmazonKinesisAsync.class, AmazonKinesisAsyncClientBuilder.standard());
                put(AmazonKinesisFirehose.class, AmazonKinesisFirehoseClientBuilder.standard());
                put(AmazonKinesisFirehoseAsync.class, AmazonKinesisFirehoseAsyncClientBuilder.standard());
                put(AmazonSNS.class, AmazonSNSClientBuilder.standard());
                put(AmazonSNSAsync.class, AmazonSNSAsyncClientBuilder.standard());
                put(AmazonSQS.class, AmazonSQSClientBuilder.standard());
                put(AmazonSQSAsync.class, AmazonSQSAsyncClientBuilder.standard());
                put(AmazonSimpleEmailService.class, AmazonSimpleEmailServiceClientBuilder.standard());
                put(AmazonSimpleEmailServiceAsync.class, AmazonSimpleEmailServiceAsyncClientBuilder.standard());
            }}.entrySet()
                .stream()
                .flatMap(entry -> Stream.of(
                    DynamicTest.dynamicTest(
                        "AWS client factory test for " + entry.getKey().getSimpleName(),
                        () -> {
                            @SuppressWarnings("unchecked") final AWSClientFactory clientFactory = new AWSClientFactory(entry.getValue());
                            final Object client = clientFactory.client(this.getClass().getDeclaredField("field"));

                            Assertions.assertTrue(entry.getKey().isInstance(client));
                        }
                    ),
                    DynamicTest.dynamicTest(
                        "Advanced default AWS client factory test for " + entry.getKey().getSimpleName(),
                        () -> {
                            @SuppressWarnings("unchecked") final AWSClientFactory clientFactory = new AWSClientFactory(entry.getValue());
                            final Object client = clientFactory.client(this.getClass().getDeclaredField("deFault"));

                            Assertions.assertTrue(entry.getKey().isInstance(client));
                        }
                    ),
                    DynamicTest.dynamicTest(
                        "Advanced custom AWS client factory test for " + entry.getKey().getSimpleName(),
                        () -> {
                            @SuppressWarnings("unchecked") final AWSClientFactory clientFactory = new AWSClientFactory(entry.getValue());
                            final Object client = clientFactory.client(this.getClass().getDeclaredField("custom"));

                            Assertions.assertTrue(entry.getKey().isInstance(client));
                        }
                    )
                ));
    }
}
