package com.example.slack.elements.structure.elements;

import com.example.slack.elements.section.elements.PlainText;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;

public class Header implements BlockBuilder, SlackElement {
    private final String type = "header";
    private PlainText text;
    private int level;

    @Override
    public String getType() {
        return type;
    }

    public Header text(String text) {
        this.text = new PlainText(text);
        return this;
    }

    public Header level(int level) {
        this.level = level;
        return this;
    }

    public PlainText getText() {
        return text;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public SlackElement build() {
        return this;
    }
}
