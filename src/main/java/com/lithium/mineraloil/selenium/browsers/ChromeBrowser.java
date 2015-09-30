package com.lithium.mineraloil.selenium.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChromeBrowser extends BrowserImpl {
    public static List<String> browserProperties = new ArrayList<>();
    URL chromePath = getClass().getClassLoader().getResource("drivers/osx/chromedriver");
    URL userDataDir = getClass().getClassLoader().getResource("chromeProfiles/profile");

    @Override
    protected WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", chromePath.getFile());
        WebDriver driver = getDriverInstance();
        logger.info("Browser User Agent: " + getUserAgent(driver));
        return driver;
    }

    protected WebDriver getDriverInstance() {
        return new ChromeDriver(getProfile());
    }

    private DesiredCapabilities getProfile(){
        DesiredCapabilities profile = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        browserProperties.add("test-type");
        browserProperties.add(String.format("user-data-dir=%s", userDataDir + UUID.randomUUID().toString().replaceAll("-.+", "").substring(0, 8)));
        options.addArguments(browserProperties);
        profile.setCapability("name", "chrome");
        profile.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        profile.setCapability("chrome.binary", chromePath.getFile());
        profile.setCapability(ChromeOptions.CAPABILITY, options);
        profile.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return profile;
    }
}
