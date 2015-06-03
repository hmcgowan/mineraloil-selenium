package com.lithium.mineraloil.selenium.elements;

import lombok.Getter;

public enum CSSAttribute {
    BACKGROUND_IMAGE("background-image"),
    BACKGROUND_COLOR("background-color"),
    BORDER_TOP_COLOR("border-top-color"),
    OPACITY("opacity"),
    DISPLAY("display"),
    FONT_STYLE("font-style"),
    BLOCK("block"),
    COLOR("color");

    @Getter
    private final String value;

    CSSAttribute(String value) {
        this.value = value;
    }
}
