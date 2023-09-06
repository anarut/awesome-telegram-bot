package com.anarut.demobot;

public enum Emoji {

    BALLOT_BOX("\u25FB"),
    BALLOT_BOX_WITH_CHECK("\u2611"),
    LEFT_ARROW("\u2B05"),
    RIGHT_ARROW("\u27A1"),
    HEAVY_CHECK_MARK("\u2705");

    private final String stringValue;

    Emoji(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
