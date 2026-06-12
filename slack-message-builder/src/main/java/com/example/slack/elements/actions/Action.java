package com.example.slack.elements.actions;

import java.util.List;

import com.example.slack.interfaces.SlackElement;

public class Action implements SlackElement {
    private final String type = "actions";

    private List<SlackElement> elements;

    public String getType() {
        return type;
    }

    public Action elements(List<SlackElement> elements) {
        this.elements = elements;
        return this;
    }

    public List<SlackElement> getElements(){
        return this.elements;
    }
    
}
