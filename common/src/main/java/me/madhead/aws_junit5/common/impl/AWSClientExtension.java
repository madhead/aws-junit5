package me.madhead.aws_junit5.common.impl;

import me.madhead.aws_junit5.common.AWSClient;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.reflect.Field;

/**
 * Base class for all AWS client injector extensions.
 */
public abstract class AWSClientExtension implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(final Object testInstance, final ExtensionContext context) throws Exception {
        for (final Field field : testInstance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(AWSClient.class)) {
                inject(testInstance, field);
            }
        }
    }

    private void inject(final Object testInstance, final Field field) throws Exception {
        @SuppressWarnings("deprecation") final boolean accessible = field.isAccessible();

        try {
            field.setAccessible(true);
            if (supports(field)) {
                field.set(testInstance, client(field));
            }
        } finally {
            field.setAccessible(accessible);
        }
    }

    /**
     * Return whether this extension is able to inject the {@code field}.
     *
     * @param field
     *     a field to inject into.
     * @return whether this extension is able to inject the {@code field}.
     */
    abstract protected boolean supports(final Field field);

    /**
     * Return an object to inject in the {@code field}.
     *
     * @param field
     *     a field to inject into.
     * @return an object to inject in the {@code field}.
     * @throws Exception
     *     when something went wrong.
     */
    abstract protected Object client(final Field field) throws Exception;
}
