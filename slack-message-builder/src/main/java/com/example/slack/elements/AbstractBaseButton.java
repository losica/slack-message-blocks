package com.example.slack.elements;

import com.example.slack.elements.section.elements.PlainText;
import com.example.slack.enums.ButtonStyle;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractBaseButton implements SlackElement, BlockBuilder {

    private static final String TYPE = "button";

    private final PlainText text;
    private final String value;
    private final String style;

    @JsonIgnore
    private final String label;

    @JsonProperty("action_id")
    private final String actionId;

    protected AbstractBaseButton(Builder<?> builder) {
        this.text = builder.text;
        this.value = builder.value;
        this.style = builder.style;
        this.actionId = builder.actionId;
        this.label = builder.label;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public PlainText getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public String getStyle() {
        return style;
    }

    public String getActionId() {
        return actionId;
    }

    public String getLabel() {
        return label;
    }

    public abstract SlackElement build();

    public abstract static class Builder<T extends Builder<T>> {

        private PlainText text;
        private String value;
        private String style;
        private String actionId;
        private String label;

        protected abstract T self();

        public T text(String text) {
            this.text = PlainText.Builder.newInstance().text(text).build();
            return self();
        }

        public T value(String value) {
            this.value = value;
            return self();
        }

        public T style(ButtonStyle style) {
            this.style = style.getValue();
            return self();
        }

        public T actionId(String actionId) {
            this.actionId = actionId;
            return self();
        }

        public T label(String label) {
            this.label = label;
            return self();
        }
    }
}