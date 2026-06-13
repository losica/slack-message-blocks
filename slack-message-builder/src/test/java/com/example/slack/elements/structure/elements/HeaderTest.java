package com.example.slack.elements.structure.elements;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

public class HeaderTest extends TestCase {

    private final ObjectMapper objectMapper =
            new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public void testBuildShouldSerializeHeaderCorrectly() throws Exception {

        Header header = new Header()
                .text("Welcome")
                .level(1);

        String json = objectMapper.writeValueAsString(header.build());
        JsonNode actual = objectMapper.readTree(json);

        String expectedJson = """
        {
          "type": "header",
          "level": 1,
          "text": {
            "type": "plain_text",
            "text": "Welcome",
            "emoji": true
          }
        }
        """;

        JsonNode expected = objectMapper.readTree(expectedJson);

        assertEquals(expected, actual);
    }

    public void testBuilderMethodsShouldSetProperties() {
        Header header = new Header()
                .text("Hello")
                .level(2);

        assertEquals("header", header.getType());
        assertEquals(2, header.getLevel());
        assertNotNull(header.getText());
    }

    public void testBuildShouldReturnSameInstance() {
        Header header = new Header();

        assertSame(header, header.build());
    }
}