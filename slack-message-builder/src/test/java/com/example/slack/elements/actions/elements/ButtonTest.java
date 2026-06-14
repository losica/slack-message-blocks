package com.example.slack.elements.actions.elements;

import com.example.slack.elements.actions.Action;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

public class ButtonTest extends TestCase {

    private ObjectMapper objectMapper;

    @Override
    protected void setUp() {
        objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public void testBuilderShouldSetFieldsCorrectly() {

        Button button = Button.Builder.newInstance()
                .text("Click me")
                .value("btn_value")
                .actionId("btn_1")
                .label("Click me")
                .build();

        assertNotNull(button);
        assertEquals("button", button.getType());
        assertEquals("btn_value", button.getValue());
        assertEquals("btn_1", button.getActionId());
        assertEquals("Click me", button.getLabel());
        assertEquals("Click me", button.getText().getText());
    }

    public void testBuildShouldWrapButtonInAction() throws Exception {

        Button button = Button.Builder.newInstance()
                .text("Submit form")
                .actionId("btn_123")
                .value("val_123")
                .build();

        Action action = (Action) button.build();

        String actualJson = objectMapper.writeValueAsString(action);
        JsonNode actual = objectMapper.readTree(actualJson);

        String expectedJson = """
        {
          "type": "actions",
          "elements": [
            {
              "type": "button",
              "text": {
                "type": "plain_text",
                "text": "Submit form",
                "emoji": true
              },
              "value": "val_123",
              "action_id": "btn_123"
            }
          ]
        }
        """;

        JsonNode expected = objectMapper.readTree(expectedJson);

        assertEquals(expected, actual);
    }

    public void testBuildShouldReturnActionInstance() {

        Button button = Button.Builder.newInstance()
                .text("Test")
                .value("v1")
                .actionId("a1")
                .build();

        Object result = button.build();

        assertTrue(result instanceof Action);
    }
}