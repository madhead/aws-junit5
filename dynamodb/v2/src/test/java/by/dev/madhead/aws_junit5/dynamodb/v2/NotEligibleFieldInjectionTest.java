package by.dev.madhead.aws_junit5.dynamodb.v2;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotEligibleFieldInjectionTest {
    @DynamoDBLocal(serviceConfiguration = SystemPropertiesDynamoDBServiceConfiguration.class)
    private String client;

    @Test
    void test() throws Exception {
        final IllegalArgumentException exception = Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new DynamoDBLocalExtension().postProcessTestInstance(this, null)
        );

        Assertions.assertEquals(
            "class java.lang.String is not supported by DynamoDBLocalExtension.",
            exception.getMessage()
        );
    }
}
