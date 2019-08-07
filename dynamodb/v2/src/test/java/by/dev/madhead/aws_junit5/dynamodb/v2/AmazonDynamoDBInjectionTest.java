package by.dev.madhead.aws_junit5.dynamodb.v2;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.Collections;
import java.util.stream.Collectors;


@ExtendWith(DynamoDBLocalExtension.class)
class AmazonDynamoDBInjectionTest {
    @DynamoDBLocal(
        serviceConfiguration = EnvironmentVariablesDynamoDBServiceConfiguration.class
    )
    private DynamoDbClient client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            client
                .listTables()
                .tableNames()
                .stream()
                .sorted()
                .collect(Collectors.toList())
        );
    }
}
