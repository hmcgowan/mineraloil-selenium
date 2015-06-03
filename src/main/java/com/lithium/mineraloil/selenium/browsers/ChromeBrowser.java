package com.lithium.mineraloil.selenium.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChromeBrowser extends BrowserImpl {
    public static List<String> browserProperties = new ArrayList<>();

    @Override
    protected WebDriver getDriver() {
        URL chromePath = getClass().getClassLoader().getResource("drivers/osx/chromedriver");
        System.setProperty("webdriver.chrome.driver", chromePath.getFile());
        WebDriver driver = getDriverInstance();
        logger.info("Browser User Agent: " + getUserAgent(driver));
        return driver;
    }

    protected WebDriver getDriverInstance() {
        return new ChromeDriver();
    }

    private DesiredCapabilities getProfile()	{
        DesiredCapabilities profile = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(browserProperties);
        profile.setCapability(ChromeOptions.CAPABILITY, options);
        profile.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return profile;
    }
}
