package com.example.slack.elements.section.elements;

import com.example.slack.elements.AbstractBaseButton;
import com.example.slack.elements.section.Section;
import com.example.slack.interfaces.SlackElement;

public class Button extends AbstractBaseButton {

    @Override
    public SlackElement build() {
        return new Section()
            .accessory(this)
            .text(new PlainText(getLabel()));
    }
}
