package com.lithium.mineraloil.selenium.generic_elements;

import java.util.List;

public interface SelectList {
    void select(String optionText);

    boolean isDisplayed();

    String getSelectedOption();

    List<String> getAvailableOptions();
}
