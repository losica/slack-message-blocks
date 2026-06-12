package com.example.slack.elements.section.elements;

import com.example.slack.elements.section.Section;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;

public class Markdown implements SlackElement, BlockBuilder  {
    private final String type = "mrkdwn";

    private String text;

    public Markdown markdown(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public SlackElement build() {
        return new Section()
            .text(this);
    }
}
