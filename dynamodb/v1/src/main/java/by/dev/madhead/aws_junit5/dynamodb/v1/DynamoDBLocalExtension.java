package by.dev.madhead.aws_junit5.dynamodb.v1;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.reflect.Field;

/**
 * Use {@code @DynamoDBLocalExtension} to extend tests with fields that are subjects for DynamoDB injection.
 */
public class DynamoDBLocalExtension implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(final Object testInstance, final ExtensionContext context) throws Exception {
        for (final Field field : testInstance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(DynamoDBLocal.class)) {
                inject(testInstance, field);
            }
        }
    }

    private void inject(final Object testInstance, final Field field) throws IllegalAccessException {
        final DynamoDBLocal configuration = field.getAnnotation(DynamoDBLocal.class);
        final boolean accessible = field.isAccessible();

        field.setAccessible(true);
        field.set(testInstance, createAmazonDynamoDB(configuration));
        field.setAccessible(accessible);
    }

    private AmazonDynamoDB createAmazonDynamoDB(final DynamoDBLocal configuration) {
        return AmazonDynamoDBClientBuilder
            .standard()
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(
                    DynamoDBLocalExtensions.getURL(configuration),
                    Regions.US_EAST_1.getName()
                )
            )
            .withCredentials(
                new AWSStaticCredentialsProvider(
                    // DynamoDB Local works with any credentials
                    new BasicAWSCredentials("", "")
                )
            )
            .build();
    }
}
