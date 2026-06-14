package com.example.slack.elements.section.elements;

import com.example.slack.elements.section.Section;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

public class MarkdownTest extends TestCase {

    private final ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public void testBuildShouldWrapMarkdownInSection() throws Exception {

        Markdown markdown = Markdown.Builder.newInstance()
                .text("Hello *world*")
                .build();

        String actualJson = objectMapper.writeValueAsString(markdown.build());
        JsonNode actual = objectMapper.readTree(actualJson);

        String expectedJson = """
        {
          "type": "section",
          "text": {
            "type": "mrkdwn",
            "text": "Hello *world*"
          }
        }
        """;

        JsonNode expected = objectMapper.readTree(expectedJson);

        assertEquals(expected, actual);
    }

    public void testBuilderShouldSetText() {

        Markdown markdown = new Markdown.Builder()
                .text("Hello")
                .build();

        assertEquals("mrkdwn", markdown.getType());
        assertEquals("Hello", markdown.getText());
    }

    public void testBuildShouldReturnSectionInstance() {

        Markdown markdown = new Markdown.Builder()
                .text("Hello")
                .build();

        Object result = markdown.build();

        assertTrue(result instanceof Section);
    }
}