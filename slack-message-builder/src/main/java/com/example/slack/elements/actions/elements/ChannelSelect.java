package com.example.slack.elements.actions.elements;

import java.util.ArrayList;
import java.util.List;

import com.example.slack.elements.actions.Action;
import com.example.slack.elements.section.elements.PlainText;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ChannelSelect implements SlackElement, BlockBuilder {

    private static final String TYPE = "channels_select";

    private final PlainText placeholder;

    @JsonProperty("action_id")
    private final String actionId;

    @JsonProperty("initial_channel")
    private final String initialChannel;

    private ChannelSelect(Builder builder) {
        this.placeholder = builder.placeholder;
        this.actionId = builder.actionId;
        this.initialChannel = builder.initialChannel;
    }

    public String getType() {
        return TYPE;
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
        return Action.Builder.newInstance().elements(blockElements).build();
    }

    public static class Builder {

        private PlainText placeholder;
        private String actionId;
        private String initialChannel;

        public Builder placeholder(String text) {
            this.placeholder = PlainText.Builder.newInstance().text(text).build();
            return this;
        }

        public static Builder newInstance()
        {
            return new Builder();
        }


        public Builder actionId(String actionId) {
            this.actionId = actionId;
            return this;
        }

        public Builder initialChannel(String initialChannel) {
            this.initialChannel = initialChannel;
            return this;
        }

        public ChannelSelect build() {
            return new ChannelSelect(this);
        }
    }
}