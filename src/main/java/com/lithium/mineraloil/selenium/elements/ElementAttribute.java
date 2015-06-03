package com.lithium.mineraloil.selenium.elements;

import lombok.Getter;

public enum ElementAttribute {
    CLASS("class"),
    DISABLED("disabled"),
    CHECKED("checked"),
    ID("id"),
    NAME("name"),
    VALUE("value"),
    STYLE("style"),
    SOURCE("src"),
    WIDTH("width"),
    HEIGHT("height"),
    HISTORY("history"),
    HREF("href"),
    TAB_INDEX("tabindex"),
    TITLE("title"),
    HTML("html"),
    INNER_HTML("innerHTML"),
    OUTER_HTML("outerHTML"),
    TOOL_TIP_LIST("tooltiplist"),
    BORDER("border");

    @Getter
    private final String value;

    ElementAttribute(String value) {
        this.value = value;
    }
}
