package by.dev.madhead.aws_junit5.common.impl;

import by.dev.madhead.aws_junit5.common.AWSClient;
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
        final AWSClient configuration = field.getAnnotation(AWSClient.class);
        final boolean accessible = field.isAccessible();

        try {
            field.setAccessible(true);
            if (supports(field)) {
                field.set(testInstance, client(field, configuration));
            } else {
                throw new IllegalArgumentException(
                    field.getType() + " is not supported by " + this.getClass().getSimpleName() + " extension."
                );
            }
        } finally {
            field.setAccessible(accessible);
        }
    }

    abstract protected boolean supports(final Field field);

    abstract protected Object client(final Field field, final AWSClient configuration) throws Exception;
}
