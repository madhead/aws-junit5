package by.dev.madhead.aws_junit5.common.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
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

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class AWSClientFactoryTest {
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
                .map((Map.Entry<Class, AwsClientBuilder> entry) -> DynamicTest.dynamicTest(
                    "AWS client factory test for " + entry.getKey().getSimpleName(),
                    () -> {
                        @SuppressWarnings("unchecked") final AWSClientFactory clientFactory = new AWSClientFactory(entry.getValue());
                        final Object client = clientFactory.createClient(field, new AWSClientImpl(MockAWSClientConfiguration.class));

                        Assertions.assertTrue(entry.getKey().isInstance(client));
                    }
                ));
    }

    static class MockAWSClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return "https://madhead.me";
        }

        @Override
        public String region() {
            return Regions.US_EAST_1.getName();
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
}
