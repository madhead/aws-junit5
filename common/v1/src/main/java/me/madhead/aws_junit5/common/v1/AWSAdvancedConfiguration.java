package me.madhead.aws_junit5.common.v1;

import com.amazonaws.ClientConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Use {@link AWSAdvancedConfiguration} to provide custom {@link ClientConfiguration} for injected clients.
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface AWSAdvancedConfiguration {
    /**
     * {@link ClientConfiguration} factory class.
     *
     * @return {@link ClientConfiguration} factory class.
     */
    Class<? extends ClientConfigurationFactory> clientConfigurationFactory() default DefaultClientConfigurationFactory.class;

    class DefaultClientConfigurationFactory implements ClientConfigurationFactory {
        @Override
        public ClientConfiguration create() {
            return null;
        }
    }
}
