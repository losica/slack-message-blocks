package com.example.slack.elements.actions;

import com.example.slack.elements.actions.elements.Button;
import com.example.slack.elements.section.elements.PlainText;
import com.example.slack.interfaces.SlackElement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

import java.util.List;

public class ActionTest extends TestCase {

    private ObjectMapper objectMapper;

    @Override
    protected void setUp() {
        objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public void testActionShouldSerializeElementsCorrectly() throws Exception {

        SlackElement plainText = PlainText.Builder.newInstance().text("You got a new message").build();

        Action action = new Action.Builder()
                .elements(List.of(plainText))
                .build();

        String actualJson = objectMapper.writeValueAsString(action.build());
        JsonNode actual = objectMapper.readTree(actualJson);

        String expectedJson = """
        {
          "type": "actions",
          "elements": [
            {
              "type": "plain_text",
              "text": "You got a new message",
              "emoji": true
            }
          ]
        }
        """;

        JsonNode expected = objectMapper.readTree(expectedJson);

        assertEquals(expected, actual);
    }

    public void testBuilderShouldSetElements() {

        SlackElement element = PlainText.Builder.newInstance().text("Test").build();

        Action action = new Action.Builder()
                .elements(List.of(element))
                .build();

        assertEquals("actions", action.getType());
        assertNotNull(action.getElements());
        assertEquals(1, action.getElements().size());
    }

    public void testBuildShouldReturnSameInstance() {

        Action action = new Action.Builder()
                .elements(List.of(PlainText.Builder.newInstance().text("Test").build()))
                .build();

        Object result = action.build();

        assertSame(action, result);
    }

}