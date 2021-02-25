package me.madhead.aws_junit5.common.impl;

import java.lang.reflect.Field;

/**
 * Creates AWS clients of type {@link T}.
 *
 * @param <T>
 *     the type of AWS clients this factory creates.
 */
public interface AWSClientFactory<T> {
    /**
     * Return an object to inject in the {@code field}.
     *
     * @param field
     *     a field to inject into.
     * @return an object to inject in the {@code field}.
     * @throws Exception
     *     when something went wrong.
     */
    T client(final Field field) throws Exception;
}
