package by.dev.madhead.aws_junit5.common.v1;

import by.dev.madhead.aws_junit5.common.AWSClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AWSClientFactoryInvalidConfigurationTest {
    @SuppressWarnings("unused")
    @AWSClient(endpoint = EmptyURLAWSEndpoint.class)
    private Object emptyURLAWSEndpoint;

    @SuppressWarnings("unused")
    @AWSClient(endpoint = NullURLAWSEndpoint.class)
    private Object nullURLAWSEndpoint;

    @Test
    void testEmpty() throws Exception {
        final IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
                @SuppressWarnings("unchecked") final AWSClientFactory clientFactory =
                    new AWSClientFactory(AmazonDynamoDBClientBuilder.standard());
                @SuppressWarnings("unused") final Object client = clientFactory.client(this.getClass().getDeclaredField(
                    "emptyURLAWSEndpoint"));
            }
        );
    }

    @Test
    void testNull() throws Exception {
        final IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
                @SuppressWarnings("unchecked") final AWSClientFactory clientFactory =
                    new AWSClientFactory(AmazonDynamoDBClientBuilder.standard());
                @SuppressWarnings("unused") final Object client = clientFactory.client(this.getClass().getDeclaredField(
                    "nullURLAWSEndpoint"));
            }
        );
    }
}
