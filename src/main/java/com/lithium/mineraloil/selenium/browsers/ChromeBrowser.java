package com.lithium.mineraloil.selenium.browsers;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class ChromeBrowser extends BrowserImpl {
    public static String downloadDirectory;

    URL chromePath = getClass().getClassLoader().getResource("drivers/osx/chromedriver");
    URL userDataDir = getClass().getClassLoader().getResource("chromeProfiles/");

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
        Map<String, Object> prefs = new HashMap<>();
        DesiredCapabilities profile = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();

        String dataDirectory = userDataDir + UUID.randomUUID().toString().replaceAll("-.+", "").substring(0, 8);
        downloadDirectory = String.format("%s", dataDirectory + "/Downloads");

        prefs.put("download.default_directory", downloadDirectory);
		prefs.put("profile.default_content_settings.popups", 0);
		options.setExperimentalOption("prefs", prefs);
        profile.setCapability("name", "chrome");
        profile.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        profile.setCapability("chrome.binary", chromePath.getFile());
        profile.setCapability(ChromeOptions.CAPABILITY, options);
        profile.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return profile;
    }
}
