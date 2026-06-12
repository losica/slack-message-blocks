package com.example.slack.elements.structure.elements;

import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;

public class Divider implements BlockBuilder, SlackElement  {
    private final String type = "divider";

    @Override
    public String getType() {
        return type;
    }

    @Override
    public SlackElement build() {
        return this;
    }
}
