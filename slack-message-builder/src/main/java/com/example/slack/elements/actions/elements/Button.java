package com.example.slack.elements.actions.elements;

import java.util.ArrayList;
import java.util.List;

import com.example.slack.elements.AbstractBaseButton;
import com.example.slack.elements.actions.Action;
import com.example.slack.interfaces.SlackElement;

public final class Button extends AbstractBaseButton {

    protected Button(Builder builder) {
        super(builder);
    }

    @Override
    public SlackElement build() {
        return new Action.Builder()
                .elements(new ArrayList<>(List.of(this))).build();
    }

    public static class Builder extends AbstractBaseButton.Builder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        public static Builder newInstance()
        {
            return new Builder();
        }

        public Button build() {
            return new Button(this);
        }
    }
}