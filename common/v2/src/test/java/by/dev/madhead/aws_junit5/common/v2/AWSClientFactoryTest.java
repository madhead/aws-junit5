package by.dev.madhead.aws_junit5.common.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;
import software.amazon.awssdk.regions.Region;
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

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class AWSClientFactoryTest {
    static class MockAWSClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return "https://madhead.me";
        }

        @Override
        public String region() {
            return Region.US_EAST_1.id();
        }

        @Override
        public String accessKey() {
            return "accessKey";
        }

        @Override
        public String secretKey() {
            return "secretKey";
        }
    }

    static class AWSClientImpl<T> implements AWSClient {
        private final Class<? extends AWSClientConfiguration> serviceConfiguration;

        AWSClientImpl(
            final Class<? extends AWSClientConfiguration> serviceConfiguration
        ) {
            this.serviceConfiguration = serviceConfiguration;
        }

        @Override
        public Class<? extends AWSClientConfiguration> clientConfiguration() {
            return serviceConfiguration;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return AWSClient.class;
        }
    }

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
            }}.entrySet()
                .stream()
                .map((Map.Entry<Class, AwsClientBuilder> entry) -> DynamicTest.dynamicTest(
                    "AWS client factory test for " + entry.getKey().getSimpleName(),
                    () -> {
                        @SuppressWarnings("unchecked") final AWSClientFactory clientFactory = new AWSClientFactory(entry.getValue());
                        final Object client = clientFactory.createClient(new AWSClientImpl(MockAWSClientConfiguration.class));

                        Assertions.assertTrue(entry.getKey().isInstance(client));
                    }
                ));
    }
}
