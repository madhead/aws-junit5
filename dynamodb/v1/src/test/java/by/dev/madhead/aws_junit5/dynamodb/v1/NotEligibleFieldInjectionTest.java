package by.dev.madhead.aws_junit5.dynamodb.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotEligibleFieldInjectionTest {
    @DynamoDBLocal(serviceConfiguration = InjectionsTestSystemPropertiesServiceConfigurationInstance1.class)
    private String amazonDynamoDB;

    @Test
    @DisplayName("Only fields of specific classes should be injected")
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
