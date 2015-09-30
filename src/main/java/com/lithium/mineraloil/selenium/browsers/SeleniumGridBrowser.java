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
import java.util.UUID;

public class SeleniumGridBrowser extends BrowserImpl {

    public static List<String> browserProperties = new ArrayList<>();
    private static URL serverAddress;
    private static URL userDataDir;

    @Override
    protected WebDriver getDriver() {
        String ip = System.getenv("TEST_IP") != null ? System.getenv("TEST_IP") : "localhost";
        serverAddress = getUrl(String.format("http://%s:4444/wd/hub", ip));
        userDataDir = getClass().getClassLoader().getResource("chromeProfiles/profile");

        WebDriver driver = getDriverInstance();
        return driver;
    }

    protected WebDriver getDriverInstance() {
        return new RemoteWebDriver(serverAddress, getProfile());
    }

    private DesiredCapabilities getProfile(){
        DesiredCapabilities profile = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        browserProperties.add("test-type");
        browserProperties.add(String.format("user-data-dir=%s", userDataDir + UUID.randomUUID().toString().replaceAll("-.+", "").substring(0, 8)));
        browserProperties.add("start-maximized");
        options.addArguments(browserProperties);
        profile.setBrowserName("chrome");
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
