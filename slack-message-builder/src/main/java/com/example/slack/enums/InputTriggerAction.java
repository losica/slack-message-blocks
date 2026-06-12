package com.example.slack.enums;

public enum InputTriggerAction {

    ON_CHARACTER_ENTERED("on_character_entered"),
    ON_ENTER_PRESSED("on_enter_pressed");

    private final String value;

    InputTriggerAction(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
