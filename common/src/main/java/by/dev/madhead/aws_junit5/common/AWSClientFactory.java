package by.dev.madhead.aws_junit5.common;

import java.lang.reflect.Field;

public interface AWSClientFactory<T> {
    T createClient(final Field field, final AWSClient configuration) throws Exception;
}
