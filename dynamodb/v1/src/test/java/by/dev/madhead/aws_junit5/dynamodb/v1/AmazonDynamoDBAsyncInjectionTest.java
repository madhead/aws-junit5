package by.dev.madhead.aws_junit5.dynamodb.v1;

import by.dev.madhead.aws_junit5.dynamodb.DynamoDBLocal;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.stream.Collectors;

@ExtendWith(DynamoDBLocalExtension.class)
class AmazonDynamoDBAsyncInjectionTest {
    @DynamoDBLocal(
        serviceConfiguration = SystemPropertiesDynamoDBServiceConfiguration.class
    )
    private AmazonDynamoDBAsync client;

    @Test
    void test() throws Exception {
        Assertions.assertNotNull(client);

        Assertions.assertEquals(
            Collections.singletonList("table"),
            client.listTablesAsync().get().getTableNames().stream().sorted().collect(Collectors.toList())
        );
    }
}
