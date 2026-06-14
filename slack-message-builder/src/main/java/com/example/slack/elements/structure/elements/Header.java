package com.example.slack.elements.structure.elements;

import com.example.slack.elements.section.elements.PlainText;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;

public final class Header implements BlockBuilder, SlackElement {

    private static final String TYPE = "header";

    private final PlainText text;
    private final int level;

    private Header(Builder builder) {
        this.text = builder.text;
        this.level = builder.level;
    }

    @Override
    public String getType() {
        return TYPE;
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


    public static class Builder {

        private PlainText text;
        private int level;

        public static Builder newInstance()
        {
            return new Builder();
        }

        public Builder text(String text) {
            this.text = PlainText.Builder.newInstance()
                    .text(text)
                    .build();
            return this;
        }

        public Builder level(int level) {
            this.level = level;
            return this;
        }

        public Header build() {
            return new Header(this);
        }
    }
}