package com.example.slack.elements.actions.elements;

import com.example.slack.elements.actions.Action;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

public class ChannelSelectTest extends TestCase {

    private ObjectMapper objectMapper;

    @Override
    protected void setUp() {
        objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public void testBuilderShouldSetFieldsCorrectly() {

        ChannelSelect channelSelect = ChannelSelect.Builder.newInstance()
                .placeholder("Select a channel")
                .actionId("channel_select_1")
                .initialChannel("C1234567890")
                .build();

        assertEquals("channels_select", channelSelect.getType());
        assertNotNull(channelSelect.getPlaceholder());
        assertEquals("Select a channel", channelSelect.getPlaceholder().getText());
        assertEquals("channel_select_1", channelSelect.getActionId());
        assertEquals("C1234567890", channelSelect.getInitialChannel());
    }

    public void testBuildShouldWrapInAction() throws Exception {

        ChannelSelect channelSelect = ChannelSelect.Builder.newInstance()
                .placeholder("Pick channel")
                .actionId("action_1")
                .initialChannel("C1111111111")
                .build();

        Action action = (Action) channelSelect.build();

        String actualJson = objectMapper.writeValueAsString(action);
        JsonNode actual = objectMapper.readTree(actualJson);

        String expectedJson = """
        {
          "type": "actions",
          "elements": [
            {
              "type": "channels_select",
              "action_id": "action_1",
              "initial_channel": "C1111111111",
              "placeholder": {
                "type": "plain_text",
                "text": "Pick channel",
                "emoji": true
              }
            }
          ]
        }
        """;

        JsonNode expected = objectMapper.readTree(expectedJson);

        assertEquals(expected, actual);
    }

    public void testBuildShouldReturnActionInstance() {

        ChannelSelect channelSelect = ChannelSelect.Builder.newInstance()
                .placeholder("Test")
                .actionId("a1")
                .initialChannel("C999")
                .build();

        Object result = channelSelect.build();

        assertTrue(result instanceof Action);
    }
}