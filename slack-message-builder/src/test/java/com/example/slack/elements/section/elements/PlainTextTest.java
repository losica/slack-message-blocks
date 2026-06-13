package com.example.slack.elements.section.elements;

import com.example.slack.elements.section.Section;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

public class PlainTextTest extends TestCase {

    private ObjectMapper objectMapper;

    @Override
    protected void setUp() {
        objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public void testBuilderShouldSetFieldsCorrectly() {

        PlainText text = new PlainText("Hello", false)
                .text("Updated")
                .emoji(true);

        assertEquals("plain_text", text.getType());
        assertEquals("Updated", text.getText());
        assertTrue(text.isEmoji());
    }

    public void testBuildShouldWrapTextInsideSection() throws Exception {

        PlainText plainText = new PlainText("Hello Slack", true);

        String actualJson = objectMapper.writeValueAsString(plainText.build());
        JsonNode actual = objectMapper.readTree(actualJson);

        String expectedJson = """
        {
          "type": "section",
          "text": {
            "type": "plain_text",
            "text": "Hello Slack",
            "emoji": true
          }
        }
        """;

        JsonNode expected = objectMapper.readTree(expectedJson);

        assertEquals(expected, actual);
    }

    public void testBuildShouldReturnSectionInstance() {
        PlainText plainText = new PlainText("Hello");

        Object result = plainText.build();

        assertTrue(result instanceof Section);
    }
}