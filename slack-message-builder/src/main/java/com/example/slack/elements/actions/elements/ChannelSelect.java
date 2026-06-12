package com.example.slack.elements.actions.elements;

import java.util.ArrayList;
import java.util.List;

import com.example.slack.elements.actions.Action;
import com.example.slack.elements.section.elements.PlainText;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChannelSelect implements SlackElement, BlockBuilder {
    private final String type = "channels_select";

    private PlainText placeholder;

    @JsonProperty("action_id")
    private String actionId;

    @JsonProperty("initial_channel")
    private String initialChannel;

    public ChannelSelect placeholder(String text) {
        this.placeholder = new PlainText(text);
        return this;
    }

    public ChannelSelect actionId(String actionId) {
        this.actionId = actionId;
        return this;
    }

    public ChannelSelect initialChannel(String initialChannel) {
        this.initialChannel = initialChannel;
        return this;
    }

    public String getType() {
        return type;
    }

    public PlainText getPlaceholder() {
        return placeholder;
    }

    public String getActionId() {
        return actionId;
    }

    public String getInitialChannel() {
        return initialChannel;
    }

     @Override
    public SlackElement build() {
        List<SlackElement> blockElements = new ArrayList<>();
        blockElements.add(this);
        return  new Action().elements(blockElements);
    }
}
