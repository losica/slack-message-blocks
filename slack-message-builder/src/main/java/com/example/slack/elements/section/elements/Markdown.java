package com.example.slack.elements.section.elements;

import com.example.slack.elements.section.Section;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;

public final class Markdown implements SlackElement, BlockBuilder {

    private static final String TYPE = "mrkdwn";

    private final String text;

    private Markdown(Builder builder) {
        this.text = builder.text;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public String getText() {
        return text;
    }

    @Override
    public SlackElement build() {
        return new Section.Builder()
                .text(this).build();
    }

    public static class Builder {

        private String text;

        public static Builder newInstance()
        {
            return new Builder();
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Markdown build() {
            return new Markdown(this);
        }
    }
}