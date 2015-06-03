package com.lithium.mineraloil.selenium.browsers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class BrowserImpl implements Browser {
    public static Logger logger = LoggerFactory.getLogger(BrowserImpl.class);
    private static WebDriver driver;

    public WebDriver open() {
        driver = getDriver();
        return driver;
    }

    public void close() {
        driver.close();
    }

    protected String getUserAgent(WebDriver driver) {
        return (String) ((JavascriptExecutor)driver).executeScript("return navigator.userAgent;");
    }


    protected abstract WebDriver getDriver();

    protected abstract WebDriver getDriverInstance();
}
