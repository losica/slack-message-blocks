package com.example.slack.elements.section.elements;

import com.example.slack.interfaces.SlackElement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

public class ButtonTest extends TestCase {

    private final ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public void testBuildShouldReturnValidSlackSectionJson() throws Exception {

        SlackElement button = Button.Builder.newInstance()
                .label("Click me")
                .value("btn-value")
                .actionId("action-123").build();

        String actualJson = objectMapper.writeValueAsString(button.build());

        String expectedJson = """
                {
                  "type": "section",
                  "text": {
                    "type": "plain_text",
                    "text": "Click me",
                    "emoji": true
                  },
                  "accessory": {
                    "type": "button",
                    "value": "btn-value",
                    "action_id": "action-123"
                  }
                }
                """;

        assertEquals(
                objectMapper.readTree(expectedJson),
                objectMapper.readTree(actualJson));
    }
}