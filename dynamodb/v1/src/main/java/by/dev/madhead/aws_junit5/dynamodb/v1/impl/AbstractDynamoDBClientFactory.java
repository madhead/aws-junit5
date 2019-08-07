package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;
import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;

public abstract class AbstractDynamoDBClientFactory<S extends AwsClientBuilder<S, T>, T> implements DynamoDBClientFactory<T> {
    private final AwsClientBuilder<S, T> awsClientBuilder;

    protected AbstractDynamoDBClientFactory(final AwsClientBuilder<S, T> awsClientBuilder) {
        this.awsClientBuilder = awsClientBuilder;
    }

    @Override
    public T createClient(final DynamoDBLocal configuration) throws Exception {
        final ServiceConfiguration serviceConfiguration = configuration.serviceConfiguration().newInstance();

        return awsClientBuilder
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(
                    serviceConfiguration.url(),
                    serviceConfiguration.region()
                )
            )
            .withCredentials(
                new AWSStaticCredentialsProvider(
                    new BasicAWSCredentials(serviceConfiguration.accessKey(), serviceConfiguration.secretKey())
                )
            )
            .build();
    }
}
