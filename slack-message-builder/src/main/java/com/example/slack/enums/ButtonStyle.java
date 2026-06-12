package com.example.slack.enums;

public enum ButtonStyle {

    PRIMARY("primary"),
    DANGER("danger");

    private final String value;

    ButtonStyle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}