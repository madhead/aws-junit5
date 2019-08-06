package by.dev.madhead.aws_junit5.dynamodb.v1.impl;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;
import by.dev.madhead.aws_junit5.dynamodb.v1.DynamoDBLocal;

import java.lang.annotation.Annotation;

public class DynamoDBLocalImpl<T> implements DynamoDBLocal {
    private final Class<? extends ServiceConfiguration> serviceConfiguration;

    public DynamoDBLocalImpl(
        final Class<? extends ServiceConfiguration> serviceConfiguration
    ) {
        this.serviceConfiguration = serviceConfiguration;
    }

    @Override
    public Class<? extends ServiceConfiguration> serviceConfiguration() {
        return serviceConfiguration;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return DynamoDBLocal.class;
    }
}
