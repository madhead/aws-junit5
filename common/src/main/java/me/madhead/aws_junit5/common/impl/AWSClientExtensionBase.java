package me.madhead.aws_junit5.common.impl;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Dictionary-based {@link AWSClientExtension} implementation.
 */
public abstract class AWSClientExtensionBase extends AWSClientExtension {
    abstract protected Map<Class<?>, ? extends AWSClientFactory<?>> factories();

    @Override
    protected boolean supports(final Field field) {
        return factories().containsKey(field.getType());
    }

    @Override
    protected Object client(final Field field) throws Exception {
        return factories().get(field.getType()).client(field);
    }
}
