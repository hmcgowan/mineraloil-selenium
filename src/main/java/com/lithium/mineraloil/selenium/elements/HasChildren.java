package com.lithium.mineraloil.selenium.elements;

import org.openqa.selenium.By;

public interface HasChildren {

    BaseElement createBaseElement(By by);

    ElementList<BaseElement> createBaseElements(By by);

    ButtonElement createButtonElement(By by);

    ElementList<ButtonElement> createButtonElements(By by);

    CheckboxElement createCheckboxElement(By by);

    ElementList<CheckboxElement> createCheckboxElements(By by);

    RadioElement createRadioElement(By by);

    ElementList<RadioElement> createRadioElements(By by);

    ImageElement createImageElement(By by);

    ElementList<ImageElement> createImageElements(By by);

    LinkElement createLinkElement(By by);

    ElementList<LinkElement> createLinkElements(By by);

    TextInputElement createTextInputElement(By by);

    ElementList<TextInputElement> createTextInputElements(By by);

    SelectListElement createSelectListElement(By by);

    ElementList<SelectListElement> createSelectListElements(By by);

    FileUploadElement createFileUploadElement(By by);

    TableElement createTableElement(By by);

    ElementList<TableElement> createTableElements(By by);

    TableRowElement createTableRowElement(By by);

    ElementList<TableRowElement> createTableRowElements(By by);
}
