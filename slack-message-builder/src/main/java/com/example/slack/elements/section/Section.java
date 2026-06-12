package com.example.slack.elements.section;

import java.util.List;

import com.example.slack.elements.section.elements.PlainText;
import com.example.slack.interfaces.BlockBuilder;
import com.example.slack.interfaces.SlackElement;

public class Section implements SlackElement, BlockBuilder {
    private final String type = "section";

    private Object text;
    private Object accessory;
    private List<SlackElement> fields;

    public Section section(String text)
    {
        this.text = new PlainText(text);
        return this;
    }

    public Section fields(List<SlackElement> fields)
    {
        this.fields = fields;
        return this;
    }

    public Section accessory(Object element)
    {
        this.accessory = element;
        return this;
    }

    public Section text(Object text) {
        this.text = text;
        return this;
    }

    @Override
    public String getType() {
        return type;
    }

    public Object getText() {
        return text;
    }

    public Object getAccessory() {
        return accessory;
    }

    public List<SlackElement> getFields() {
        return this.fields;
    }

    @Override
    public SlackElement build() {
        return this;
    }
}
