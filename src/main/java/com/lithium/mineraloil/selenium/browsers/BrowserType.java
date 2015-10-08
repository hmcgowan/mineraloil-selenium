package com.lithium.mineraloil.selenium.browsers;

import org.openqa.selenium.WebDriver;

public enum BrowserType {
    FIREFOX {
        @Override
        public WebDriver create() {
            return new FirefoxBrowser().open();
        }

        @Override
        public String getDownloadDirectory() {
            return FirefoxBrowser.downloadDirectory;
        }
    }, CHROME {
        @Override
        public WebDriver create() {
            return new ChromeBrowser().open();
        }

        @Override
        public String getDownloadDirectory() {
            return ChromeBrowser.downloadDirectory;
        }
    }, SELENIUM_GRID {
        @Override
        public WebDriver create() {
            return new SeleniumGridBrowser().open();
        }
        @Override
        public String getDownloadDirectory() {
            return SeleniumGridBrowser.downloadDirectory;
        }
    };

    public abstract WebDriver create();
    public abstract String getDownloadDirectory();
}
