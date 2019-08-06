package by.dev.madhead.aws_junit5.dynamodb;

import by.dev.madhead.aws_junit5.common.ServiceConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * {@code @DynamoDBLocal} is used to annotate fields that are subjects for DynamoDB injection.
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface DynamoDBLocal {
    /**
     * AWS service configuration provider implementation class.
     *
     * @return AWS service configuration provider implementation class.
     */
    Class<? extends ServiceConfiguration> serviceConfiguration();
}
