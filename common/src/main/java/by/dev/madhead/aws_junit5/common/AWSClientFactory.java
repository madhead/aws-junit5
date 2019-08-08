package by.dev.madhead.aws_junit5.common;

public interface AWSClientFactory<T> {
    T createClient(final AWSClient configuration) throws Exception;
}
