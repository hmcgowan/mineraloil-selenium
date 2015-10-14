package com.lithium.mineraloil.selenium.browsers;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class SeleniumGridBrowser extends BrowserImpl {
    public static String downloadDirectory;
    
    private static URL serverAddress;
    private static URL userDataDir;

    @Override
    protected WebDriver getDriver() {
        String ip = System.getenv("TEST_IP") != null ? System.getenv("TEST_IP") : "localhost";
        serverAddress = getUrl(String.format("http://%s:4444/wd/hub", ip));
        userDataDir = getClass().getClassLoader().getResource("chromeProfiles");

        logger.info(String.format("Attempting to connect to %s", ip));

        WebDriver driver = getDriverInstance();
        return driver;
    }

    protected WebDriver getDriverInstance() {
        return new RemoteWebDriver(serverAddress, getProfile());
    }

    private DesiredCapabilities getProfile(){
        Map<String, Object> prefs = new HashMap<>();
        DesiredCapabilities profile = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();

        String dataDirectory = userDataDir + UUID.randomUUID().toString().replaceAll("-.+", "").substring(0, 8);
        downloadDirectory = String.format("%s", dataDirectory + "/Downloads");

        prefs.put("download.default_directory", downloadDirectory);
        options.addArguments("start-maximized");
        options.setExperimentalOption("prefs", prefs);
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
