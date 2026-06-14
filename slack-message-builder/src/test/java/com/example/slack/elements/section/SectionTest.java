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

        Section section = Section.Builder.newInstance()
                .section("Hello world")
                .build();

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

        Section section = new Section
                .Builder()
                .text("Main text")
                .accessory(PlainText.Builder.newInstance().text("Button label").build())
                .build();

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

        SlackElement field = PlainText.Builder.newInstance().text("Field 1").build();

        Section section = new Section.Builder()
                .fields(List.of(field)).build();

        assertNotNull(section.getFields());
        assertEquals(1, section.getFields().size());
    }

    public void testBuildShouldReturnSameInstance() {

        Section section = Section.Builder.newInstance().build();

        Object result = section.build();

        assertSame(section, result);
    }
}