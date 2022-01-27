package me.madhead.aws_junit5.common.v2;

import me.madhead.aws_junit5.common.AWSClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

class AWSClientFactoryInvalidConfigurationTest {
    @SuppressWarnings("unused")
    @AWSClient(endpoint = EmptyURLAWSEndpoint.class)
    private Object emptyURLAWSEndpoint;

    @SuppressWarnings("unused")
    @AWSClient(endpoint = NullURLAWSEndpoint.class)
    private Object nullURLAWSEndpoint;

    @Test
    void testEmpty() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
                final AWSClientFactory<?, ?> clientFactory = new AWSClientFactory<>(DynamoDbClient.builder());

                clientFactory.client(this.getClass().getDeclaredField("emptyURLAWSEndpoint"));
            }
        );
    }

    @Test
    void testNull() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
                final AWSClientFactory<?, ?> clientFactory = new AWSClientFactory<>(DynamoDbClient.builder());

                clientFactory.client(this.getClass().getDeclaredField("nullURLAWSEndpoint"));
            }
        );
    }
}
