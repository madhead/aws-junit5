package by.dev.madhead.aws_junit5.common.v2;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSClientConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.lang.annotation.Annotation;

class AWSClientFactoryInvalidConfigurationTest {
    @Test
    void testEmpty() throws Exception {
        final IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
                @SuppressWarnings("unchecked") final AWSClientFactory clientFactory =
                    new AWSClientFactory(DynamoDbClient.builder());
                final Object client = clientFactory.createClient(new AWSClientImpl(EmptyURLAWSClientConfiguration.class));
            }
        );
    }

    @Test
    void testNull() throws Exception {
        final IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
                @SuppressWarnings("unchecked") final AWSClientFactory clientFactory =
                    new AWSClientFactory(DynamoDbClient.builder());
                final Object client = clientFactory.createClient(new AWSClientImpl(NullURLAWSClientConfiguration.class));
            }
        );
    }

    static class EmptyURLAWSClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return "";
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

    static class NullURLAWSClientConfiguration implements AWSClientConfiguration {
        @Override
        public String url() {
            return null;
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
}
