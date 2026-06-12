package com.example.slack.elements.section.elements;

import com.example.slack.elements.section.Section;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;

public class PlainText implements SlackElement, BlockBuilder {
    private final String type = "plain_text";
    private String text;
    private boolean emoji;

    public PlainText(String text) {
        this(text, true);
    }

    public PlainText(String text, boolean emoji) {
        this.text = text;
        this.emoji = emoji;
    }

    public PlainText text(String text) {
        this.text = text;
        return this;
    }

    public PlainText emoji(boolean emoji) {
        this.emoji = emoji;
        return this;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public boolean isEmoji() {
        return emoji;
    }

    @Override
    public SlackElement build() {
        return new Section()
            .section(this.text);
    }
}
