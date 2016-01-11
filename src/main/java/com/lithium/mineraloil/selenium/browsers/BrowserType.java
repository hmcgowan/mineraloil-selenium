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
    }, REMOTE_CHROME {
        @Override
        public WebDriver create() {
            return new RemoteChromeBrowser().open();
        }
        @Override
        public String getDownloadDirectory() {
            return RemoteChromeBrowser.downloadDirectory;
        }
    }, REMOTE_FIREFOX {
        @Override
        public WebDriver create() { return new RemoteFirefoxBrowser().open(); }
        @Override
        public String getDownloadDirectory() { return RemoteFirefoxBrowser.downloadDirectory; }
    };

    public abstract WebDriver create();
    public abstract String getDownloadDirectory();
}
