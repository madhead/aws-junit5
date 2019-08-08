package by.dev.madhead.aws_junit5.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@code @AWSClient} is used to annotate fields that are subjects for AWS client injection.
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface AWSClient {
    /**
     * AWS client configuration provider implementation class.
     *
     * @return AWS client configuration provider implementation class.
     */
    Class<? extends AWSClientConfiguration> clientConfiguration();
}
