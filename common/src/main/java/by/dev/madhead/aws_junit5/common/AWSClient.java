package by.dev.madhead.aws_junit5.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Use {@link AWSClient} to annotate fields in test classes that are subjects for AWS client injection.
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface AWSClient {
    /**
     * AWS service endpoint configuration.
     *
     * @return AWS service endpoint configuration.
     */
    Class<? extends AWSEndpoint> endpoint();
}
