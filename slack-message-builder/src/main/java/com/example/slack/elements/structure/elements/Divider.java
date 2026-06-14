package com.example.slack.elements.structure.elements;

import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;

public final class Divider implements BlockBuilder, SlackElement  {
    private final String type = "divider";

    private Divider(Builder builder) {
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public SlackElement build() {
        return this;
    }

    public static class Builder {

        public static Builder newInstance()
        {
            return new Builder();
        }

        public Divider build() {
            return new Divider(this);
        }
    }
}
