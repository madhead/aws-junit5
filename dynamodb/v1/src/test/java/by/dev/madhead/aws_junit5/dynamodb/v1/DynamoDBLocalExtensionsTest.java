package by.dev.madhead.aws_junit5.dynamodb.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.PreconditionViolationException;

import java.lang.annotation.Annotation;
import java.util.StringJoiner;

class DynamoDBLocalExtensionsTest {
    //@formatter:off
    private static Object[][] configurations() {
        return new Object[][]{
            {new DynamoDBLocalImpl("url", "", ""), "url"},

            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.void", ""), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.null", ""), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.empty", ""), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.dynamodb.v1.url1", ""), "url"},

            {new DynamoDBLocalImpl("url", "", "BY_DEV_MADHEAD_AWS_JUNIT5_VOID"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.void", "BY_DEV_MADHEAD_AWS_JUNIT5_VOID"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.null", "BY_DEV_MADHEAD_AWS_JUNIT5_VOID"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.empty", "BY_DEV_MADHEAD_AWS_JUNIT5_VOID"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.dynamodb.v1.url1", "BY_DEV_MADHEAD_AWS_JUNIT5_VOID"), "url"},

            {new DynamoDBLocalImpl("url", "", "BY_DEV_MADHEAD_AWS_JUNIT5_NULL"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.void", "BY_DEV_MADHEAD_AWS_JUNIT5_NULL"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.null", "BY_DEV_MADHEAD_AWS_JUNIT5_NULL"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.empty", "BY_DEV_MADHEAD_AWS_JUNIT5_NULL"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.dynamodb.v1.url1", "BY_DEV_MADHEAD_AWS_JUNIT5_NULL"), "url"},

            {new DynamoDBLocalImpl("url", "", "BY_DEV_MADHEAD_AWS_JUNIT5_EMPTY"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.void", "BY_DEV_MADHEAD_AWS_JUNIT5_EMPTY"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.null", "BY_DEV_MADHEAD_AWS_JUNIT5_EMPTY"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.empty", "BY_DEV_MADHEAD_AWS_JUNIT5_EMPTY"), "url"},
            {new DynamoDBLocalImpl("url", "by.dev.madhead.aws_junit5.dynamodb.v1.url1", "BY_DEV_MADHEAD_AWS_JUNIT5_EMPTY"), "url"},

            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.dynamodb.v1.url1", ""), "http://dynamodb-local-1:8000"},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.dynamodb.v1.url1", "BY_DEV_MADHEAD_AWS_JUNIT5_VOID"), "http://dynamodb-local-1:8000"},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.dynamodb.v1.url1", "BY_DEV_MADHEAD_AWS_JUNIT5_NULL"), "http://dynamodb-local-1:8000"},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.dynamodb.v1.url1", "BY_DEV_MADHEAD_AWS_JUNIT5_EMPTY"), "http://dynamodb-local-1:8000"},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.dynamodb.v1.url1", "BY_DEV_MADHEAD_URL"), "http://dynamodb-local-1:8000"},

            {new DynamoDBLocalImpl("", "", "BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL1"), "http://dynamodb-local-1:8000"},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.void", "BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL1"), "http://dynamodb-local-1:8000"},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.null", "BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL1"), "http://dynamodb-local-1:8000"},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.empty", "BY_DEV_MADHEAD_AWS_JUNIT5_DYNAMODB_V1_URL1"), "http://dynamodb-local-1:8000"},
        };
    }

    private static Object[][] illegalConfigurations() {
        return new Object[][]{
            {new DynamoDBLocalImpl("", "", "")},

            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.void", "")},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.null", "")},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.empty", "")},

            {new DynamoDBLocalImpl("", "", "BY_DEV_MADHEAD_AWS_JUNIT5_VOID")},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.void", "BY_DEV_MADHEAD_AWS_JUNIT5_VOID")},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.null", "BY_DEV_MADHEAD_AWS_JUNIT5_VOID")},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.empty", "BY_DEV_MADHEAD_AWS_JUNIT5_VOID")},

            {new DynamoDBLocalImpl("", "", "BY_DEV_MADHEAD_AWS_JUNIT5_NULL")},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.void", "BY_DEV_MADHEAD_AWS_JUNIT5_NULL")},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.null", "BY_DEV_MADHEAD_AWS_JUNIT5_NULL")},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.empty", "BY_DEV_MADHEAD_AWS_JUNIT5_NULL")},

            {new DynamoDBLocalImpl("", "", "BY_DEV_MADHEAD_AWS_JUNIT5_EMPTY")},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.void", "BY_DEV_MADHEAD_AWS_JUNIT5_EMPTY")},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.null", "BY_DEV_MADHEAD_AWS_JUNIT5_EMPTY")},
            {new DynamoDBLocalImpl("", "by.dev.madhead.aws_junit5.empty", "BY_DEV_MADHEAD_AWS_JUNIT5_EMPTY")},
        };
    }
    //@formatter:on

    @ParameterizedTest
    @MethodSource("configurations")
    @DisplayName("URL should be calculated based on defined order of precedence")
    void test(final DynamoDBLocal configuration, final String expected) {
        Assertions.assertEquals(expected, DynamoDBLocalExtensions.getURL(configuration));
    }

    @ParameterizedTest
    @MethodSource("illegalConfigurations")
    @DisplayName("Exception should be thrown if the URL is not provided")
    void testIllegal(final DynamoDBLocal configuration) {
        Assertions.assertThrows(PreconditionViolationException.class, () -> DynamoDBLocalExtensions.getURL(configuration));
    }

    private static class DynamoDBLocalImpl implements DynamoDBLocal {
        private final String url;
        private final String urlSystemProperty;
        private final String urlEnvironmentVariable;

        private DynamoDBLocalImpl(
            final String url,
            final String urlSystemProperty,
            final String urlEnvironmentVariable
        ) {
            this.url = url;
            this.urlSystemProperty = urlSystemProperty;
            this.urlEnvironmentVariable = urlEnvironmentVariable;
        }

        @Override
        public String url() {
            return url;
        }

        @Override
        public String urlSystemProperty() {
            return urlSystemProperty;
        }

        @Override
        public String urlEnvironmentVariable() {
            return urlEnvironmentVariable;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return DynamoDBLocal.class;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", DynamoDBLocalImpl.class.getSimpleName() + "[", "]")
                .add("url='" + url + "'")
                .add("urlSystemProperty='" + urlSystemProperty + "'")
                .add("urlEnvironmentVariable='" + urlEnvironmentVariable + "'")
                .toString();
        }
    }
}
