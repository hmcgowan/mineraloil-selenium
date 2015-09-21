package com.lithium.mineraloil.selenium.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SeleniumGridBrowser extends BrowserImpl {

    public static List<String> browserProperties = new ArrayList<>();
    private static URL serverAddress;
    private static URL chromePath;

    @Override
    protected WebDriver getDriver() {
        serverAddress = getUrl("http://localhost:4444/wd/hub");
        chromePath = getUrl(getClass().getClassLoader().getResource("drivers/osx/chromedriver").toString());
        WebDriver driver = getDriverInstance();
        System.setProperty("webdriver.chrome.driver", chromePath.getFile());
        return driver;
    }

    protected WebDriver getDriverInstance() {
        return new RemoteWebDriver(serverAddress, getProfile());
    }

    private DesiredCapabilities getProfile(){
        DesiredCapabilities profile = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(browserProperties);
        options.addArguments("test-type");
        profile.setBrowserName("chrome");
        profile.setCapability("chrome.binary", chromePath.getFile());
        profile.setCapability(ChromeOptions.CAPABILITY, options);
        profile.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return profile;
    }

    private URL getUrl(String aUrl) {
        URL theURL = null;
        try {
            theURL = new URL(aUrl);
        } catch (MalformedURLException e) {
            logger.info("The URL was malformed", e);
        }
        return theURL;
    }
}
