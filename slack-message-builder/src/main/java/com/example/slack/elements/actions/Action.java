package com.example.slack.elements.actions;

import java.util.List;

import com.example.slack.interfaces.SlackElement;

public final class Action implements SlackElement {

    private static final String TYPE = "actions";

    private final List<SlackElement> elements;

    private Action(Builder builder) {
        this.elements = List.copyOf(builder.elements);
    }

    public String getType() {
        return TYPE;
    }

    public List<SlackElement> getElements() {
        return elements;
    }

    @Override
    public SlackElement build() {
        return this;
    }

    public static class Builder {

        private List<SlackElement> elements;

        public static Builder newInstance()
        {
            return new Builder();
        }


        public Builder elements(List<SlackElement> elements) {
            this.elements = elements;
            return this;
        }

        public Action build() {

            return new Action(this);
        }
    }
}