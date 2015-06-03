package com.lithium.mineraloil.selenium.browsers;

import org.openqa.selenium.WebDriver;

public interface Browser {
    WebDriver open();
    void close();
}
