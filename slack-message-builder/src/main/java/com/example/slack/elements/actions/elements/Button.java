package com.example.slack.elements.actions.elements;

import java.util.ArrayList;
import java.util.List;

import com.example.slack.elements.AbstractBaseButton;
import com.example.slack.elements.actions.Action;
import com.example.slack.interfaces.SlackElement;

public abstract class Button extends AbstractBaseButton {
  
    @Override
    public SlackElement build() {
        return new Action()
            .elements(new ArrayList<>(List.of(this)));
    }
}
