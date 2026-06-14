package com.example.slack.elements.structure.elements;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;

public class DividerTest extends TestCase {

    private final ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public void testBuildShouldSerializeToSlackDividerJson() throws Exception {
        Divider divider = Divider.Builder.newInstance().build();

        String json = objectMapper.writeValueAsString(divider.build());

        JsonNode expected = objectMapper.readTree("""
                {
                  "type": "divider"
                }
                """);
        JsonNode actual = objectMapper.readTree(json);

        assertEquals(expected, actual);
    }
}