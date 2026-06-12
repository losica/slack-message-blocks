package com.example.slack.enums;

public enum ListStyle {
    ORDERED("ordered"),
    BULLET("bullet");
    
    private final String value;

    ListStyle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
