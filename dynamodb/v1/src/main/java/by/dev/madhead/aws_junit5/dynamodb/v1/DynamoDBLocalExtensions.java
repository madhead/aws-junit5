package by.dev.madhead.aws_junit5.dynamodb.v1;

import org.junit.platform.commons.util.PreconditionViolationException;

/**
 * Kotlin-inspired extension functions for {@link DynamoDBLocal}, but in Java.
 */
public class DynamoDBLocalExtensions {
    public static String getURL(final DynamoDBLocal configuration) {
        String url = configuration.url();

        if ((url.isEmpty()) && (!configuration.urlSystemProperty().isEmpty())) {
            url = System.getProperty(configuration.urlSystemProperty());
        }
        if ((url == null) || (url.isEmpty())) {
            url = System.getenv(configuration.urlEnvironmentVariable());
        }
        if ((url == null) || (url.isEmpty())) {
            throw new PreconditionViolationException("No URL for DynamoDB Local provided");
        }

        return url;
    }
}
