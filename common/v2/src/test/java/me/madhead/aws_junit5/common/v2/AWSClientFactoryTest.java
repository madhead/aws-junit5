package me.madhead.aws_junit5.common.v2;

import me.madhead.aws_junit5.common.AWSClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsAsyncClient;
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient;
import software.amazon.awssdk.services.firehose.FirehoseAsyncClient;
import software.amazon.awssdk.services.firehose.FirehoseClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.ses.SesAsyncClient;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;

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
        clientOverrideConfigurationFactory = CustomClientOverrideConfigurationFactory.class,
        sdkHttpClientFactory = CustomSdkHttpClientFactory.class,
        sdkAsyncHttpClientFactory = CustomSdkAsyncHttpClientFactory.class
    )
    private Object custom;

    @TestFactory
    Stream<DynamicTest> test() {
        return
            new HashMap<Class, AwsClientBuilder>() {{
                put(DynamoDbClient.class, DynamoDbClient.builder());
                put(DynamoDbAsyncClient.class, DynamoDbAsyncClient.builder());
                put(DynamoDbStreamsClient.class, DynamoDbStreamsClient.builder());
                put(DynamoDbStreamsAsyncClient.class, DynamoDbStreamsAsyncClient.builder());
                put(S3Client.class, S3Client.builder());
                put(S3AsyncClient.class, S3AsyncClient.builder());
                put(KinesisClient.class, KinesisClient.builder());
                put(KinesisAsyncClient.class, KinesisAsyncClient.builder());
                put(FirehoseClient.class, FirehoseClient.builder());
                put(FirehoseAsyncClient.class, FirehoseAsyncClient.builder());
                put(SnsClient.class, SnsClient.builder());
                put(SnsAsyncClient.class, SnsAsyncClient.builder());
                put(SqsClient.class, SqsClient.builder());
                put(SqsAsyncClient.class, SqsAsyncClient.builder());
                put(SesClient.class, SesClient.builder());
                put(SesAsyncClient.class, SesAsyncClient.builder());
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
