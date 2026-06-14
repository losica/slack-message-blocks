package com.example.slack.elements.section.elements;

import com.example.slack.elements.AbstractBaseButton;
import com.example.slack.elements.section.Section;
import com.example.slack.interfaces.SlackElement;

public class Button extends AbstractBaseButton {

    protected Button(Builder builder) {
        super(builder);
    }

    @Override
    public SlackElement build() {
        return new Section.Builder()
                .accessory(this)
                .text(
                        PlainText.Builder.newInstance()
                            .text(getLabel())
                            .build())
                .build();
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
