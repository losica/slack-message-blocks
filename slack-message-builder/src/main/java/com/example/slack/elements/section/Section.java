package com.example.slack.elements.section;

import java.util.List;

import com.example.slack.elements.section.elements.PlainText;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;

public final class Section implements SlackElement, BlockBuilder {

    private static final String TYPE = "section";

    private final Object text;
    private final Object accessory;
    private final List<SlackElement> fields;

    private Section(Builder builder) {
        this.text = builder.text;
        this.accessory = builder.accessory;
        this.fields = builder.fields == null ? null : List.copyOf(builder.fields);
    }

    public String getType() {
        return TYPE;
    }

    public Object getText() {
        return text;
    }

    public Object getAccessory() {
        return accessory;
    }

    public List<SlackElement> getFields() {
        return fields;
    }

    @Override
    public SlackElement build() {
        return this;
    }

    public static class Builder {

        private Object text;
        private Object accessory;
        private List<SlackElement> fields;

        public static Builder newInstance()
        {
            return new Builder();
        }

        public Builder section(String text) {
            this.text = PlainText.Builder.newInstance()
                    .text(text)
                    .build();
            return this;
        }

        public Builder text(Object text) {
            this.text = text;
            return this;
        }

        public Builder accessory(Object accessory) {
            this.accessory = accessory;
            return this;
        }

        public Builder fields(List<SlackElement> fields) {
            this.fields = fields;
            return this;
        }

        public Section build() {
            return new Section(this);
        }
    }
}