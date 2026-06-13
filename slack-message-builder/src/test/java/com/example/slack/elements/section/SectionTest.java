package com.example.slack.elements.section;

import com.example.slack.elements.section.elements.PlainText;
import com.example.slack.interfaces.SlackElement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

import java.util.List;

public class SectionTest extends TestCase {

    private ObjectMapper objectMapper;

    @Override
    protected void setUp() {
        objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public void testSectionWithTextShouldSerializeCorrectly() throws Exception {

        Section section = new Section()
                .section("Hello world");

        String actualJson = objectMapper.writeValueAsString(section.build());
        JsonNode actual = objectMapper.readTree(actualJson);

        String expectedJson = """
        {
          "type": "section",
          "text": {
            "type": "plain_text",
            "text": "Hello world",
            "emoji": true
          }
        }
        """;

        JsonNode expected = objectMapper.readTree(expectedJson);

        assertEquals(expected, actual);
    }

    public void testSectionWithAccessoryShouldSerializeCorrectly() throws Exception {

        Section section = new Section()
                .text("Main text")
                .accessory(new PlainText("Button label"));

        String actualJson = objectMapper.writeValueAsString(section.build());
        JsonNode actual = objectMapper.readTree(actualJson);

        String expectedJson = """
        {
          "type": "section",
          "text": "Main text",
          "accessory": {
            "type": "plain_text",
            "text": "Button label",
            "emoji": true
          }
        }
        """;

        JsonNode expected = objectMapper.readTree(expectedJson);

        assertEquals(expected, actual);
    }

    public void testSectionWithFieldsShouldExposeFields() {

        SlackElement field = new PlainText("Field 1");

        Section section = new Section()
                .fields(List.of(field));

        assertNotNull(section.getFields());
        assertEquals(1, section.getFields().size());
    }

    public void testBuildShouldReturnSameInstance() {

        Section section = new Section();

        Object result = section.build();

        assertSame(section, result);
    }
}