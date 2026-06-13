package com.example.slack.elements;

import com.example.slack.elements.section.elements.PlainText;
import com.example.slack.enums.ButtonStyle;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractBaseButton implements SlackElement, BlockBuilder {
    private final String type = "button";

    private PlainText text;
    private String value;
    private String style;
    
    @JsonIgnore
    private String label;

    @JsonProperty("action_id")
    private String actionId;

    public AbstractBaseButton text(String text) {
        this.text = new PlainText(text);
        return this;
    }

    public AbstractBaseButton value(String value) {
        this.value = value;
        return this;
    }

    public AbstractBaseButton style(ButtonStyle style) {
        this.style = style.getValue();
        return this;
    }

    public AbstractBaseButton actionId(String actionId) {
        this.actionId = actionId;
        return this;
    }

    public AbstractBaseButton label(String label) {
        this.label = label;
        return this;
    }

    @Override
    public String getType() {
        return type;
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
}
