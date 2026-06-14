package com.example.slack.elements.section.elements;

import com.example.slack.elements.section.Section;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;

public final class PlainText implements SlackElement, BlockBuilder {

    private static final String TYPE = "plain_text";

    private final String text;
    private final boolean emoji;

    private PlainText(Builder builder) {
        this.text = builder.text;
        this.emoji = builder.emoji;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public String getText() {
        return text;
    }

    public boolean isEmoji() {
        return emoji;
    }

    @Override
    public SlackElement build() {
        return Section.Builder.newInstance()
                .section(text)
                .build();
    }

    public static class Builder {

        private String text;
        private boolean emoji = true;

        public static Builder newInstance()
        {
            return new Builder();
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder emoji(boolean emoji) {
            this.emoji = emoji;
            return this;
        }

        public PlainText build() {
            return new PlainText(this);
        }
    }
}