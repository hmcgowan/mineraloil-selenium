package com.lithium.mineraloil.selenium.elements;

import com.lithium.mineraloil.selenium.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public interface ElementActions {

    public WebElement locateElement();

    void click();

    void doubleClick();

    void clickNoHover();

    String getAttribute(ElementAttribute attributeName);

    String getTagName();

    String getCssValue(CSSAttribute attributeName);

    String getText();

    Element getParentElement();

    By getBy();

    boolean isInDOM();

    boolean isDisplayed();

    boolean isEnabled();

    boolean isSelected();

    boolean focus();

    boolean isFocused();

    void hover();

    void sendKeys(final Keys... keys);

    void fireBlurEvent();

    void scrollIntoView();

    default WebElement flash() {
        final WebElement element = locateElement();
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getCurrentWebDriver();
        String elementColor = (String)js.executeScript("arguments[0].style.backgroundColor", element);
        elementColor = (elementColor == null) ? "" : elementColor;
        for(int i = 0; i < 20; i++) {
            String bgColor = (i % 2 == 0) ? "red" : elementColor;
            js.executeScript(String.format("arguments[0].style.backgroundColor = '%s'", bgColor), element);
        }
        js.executeScript("arguments[0].style.backgroundColor = arguments[1]", element, elementColor);
        return element;
    }

}
