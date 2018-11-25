package by.dev.madhead.aws_junit5.dynamodb.v1;

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
     * Raw URL to a DynamoDB Local, like {@code "http://localhost:8000"}.
     * <p>
     * {@code url} has the highest order of precedence among {@code url}, {@link #urlSystemProperty} and {@link #urlEnvironmentVariable},
     * so whatever value you set here will win and others will be ignored. At least one of these values must be provided.
     *
     * @return raw URL to a DynamoDB Local
     */
    String url() default "";

    /**
     * Name of a <a href="https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html">Java System property</a>
     * holding the value of a URL to a DynamoDB Local. E.g. if you're running a test with {@code -Ddynamodb.url=http://localhost:8000},
     * pass {@code "dynamodb.url"} here.
     * <p>
     * {@code urlSystemProperty} has medium order of precedence among {@link #url}, {@code urlSystemProperty} and
     * {@link #urlEnvironmentVariable}: it will loose to {@link #url} and it will override {@link #urlEnvironmentVariable}. At least one
     * of these values must be provided.
     *
     * @return Name of a Java System property holding the value of a URL to a DynamoDB Local.
     */
    String urlSystemProperty() default "";

    /**
     * Name of an <a href="https://en.wikipedia.org/wiki/Environment_variable">environment variable</a>
     * holding the value of a URL to a DynamoDB Local. E.g. if you're running a test with {@code DYNAMODB_URL=http://localhost:8000},
     * pass {@code "DYNAMODB_URL"} here.
     * <p>
     * {@code urlEnvironmentVariable} has the lowest order of precedence among {@link #url}, {@link #urlSystemProperty} and {@code
     * urlEnvironmentVariable}, so whenever any other of these values is set {@code urlEnvironmentVariable} is ignored. At least one of
     * these values must be provided.
     *
     * @return Name of an Environment variable holding the value of a URL to a DynamoDB Local.
     */
    String urlEnvironmentVariable() default "";
}
