package by.dev.madhead.aws_junit5.dynamodb.v1;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.*;
import java.util.stream.Collectors;

@ExtendWith(DynamoDBLocalExtension.class)
class AmazonDynamoDBAsyncInjectionsTest {
    @DynamoDBLocal(
        serviceConfiguration = InjectionsTestSystemPropertiesServiceConfigurationInstance1.class
    )
    private AmazonDynamoDBAsync amazonDynamoDBAsyncBySystemProperty;

    @DynamoDBLocal(
        serviceConfiguration = InjectionsTestEnvironmentVariablesServiceConfigurationInstance2.class
    )
    private AmazonDynamoDBAsync amazonDynamoDBAsyncByEnvironmentVariable;

    @Test
    @DisplayName("AmazonDynamoDBAsync should be injected and configured properly with system properties")
    void test1() throws Exception {
        Assertions.assertNotNull(amazonDynamoDBAsyncBySystemProperty);

        Assertions.assertEquals(
            Collections.singletonList("table_1"),
            amazonDynamoDBAsyncBySystemProperty.listTablesAsync().get().getTableNames().stream().sorted().collect(Collectors.toList())
        );
        Assertions.assertEquals(
            new LinkedList<HashMap<String, AttributeValue>>() {{
                add(
                    new HashMap<String, AttributeValue>() {{
                        put("id", new AttributeValue("unit-test-table_1-001"));
                        put("data", new AttributeValue("unit-test-table_1-001"));
                    }}
                );
                add(
                    new HashMap<String, AttributeValue>() {{
                        put("id", new AttributeValue("unit-test-table_1-002"));
                        put("data", new AttributeValue("unit-test-table_1-002"));
                    }}
                );
            }},
            amazonDynamoDBAsyncBySystemProperty
                .scanAsync("table_1", (List<String>) null)
                .get()
                .getItems()
                .stream()
                .sorted(Comparator.comparing(item -> item.get("id").getS()))
                .collect(Collectors.toList())
        );
    }

    @Test
    @DisplayName("AmazonDynamoDBAsync should be injected and configured properly with environment variables")
    void test2() throws Exception {
        Assertions.assertNotNull(amazonDynamoDBAsyncByEnvironmentVariable);

        Assertions.assertEquals(
            Collections.singletonList("table_2"),
            amazonDynamoDBAsyncByEnvironmentVariable.listTablesAsync().get().getTableNames().stream().sorted().collect(Collectors.toList())
        );
        Assertions.assertEquals(
            new LinkedList<HashMap<String, AttributeValue>>() {{
                add(
                    new HashMap<String, AttributeValue>() {{
                        put("id", new AttributeValue("unit-test-table_2-001"));
                        put("data", new AttributeValue("unit-test-table_2-001"));
                    }}
                );
                add(
                    new HashMap<String, AttributeValue>() {{
                        put("id", new AttributeValue("unit-test-table_2-002"));
                        put("data", new AttributeValue("unit-test-table_2-002"));
                    }}
                );
            }},
            amazonDynamoDBAsyncByEnvironmentVariable
                .scanAsync("table_2", (List<String>) null)
                .get()
                .getItems()
                .stream()
                .sorted(Comparator.comparing(item -> item.get("id").getS()))
                .collect(Collectors.toList())
        );
    }
}
