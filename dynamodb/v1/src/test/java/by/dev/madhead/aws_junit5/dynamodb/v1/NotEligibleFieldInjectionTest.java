package by.dev.madhead.aws_junit5.dynamodb.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotEligibleFieldInjectionTest {
    @DynamoDBLocal(url = "http://localhost:8000")
    private String amazonDynamoDB;

    @Test
    @DisplayName("Only fields of specific classes should be injected")
    void test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DynamoDBLocalExtension().postProcessTestInstance(this, null));
    }
}
