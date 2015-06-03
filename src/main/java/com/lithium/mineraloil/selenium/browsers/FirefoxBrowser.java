package com.lithium.mineraloil.selenium.browsers;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class FirefoxBrowser extends BrowserImpl {
    public static String downloadDirectory;
    public static List<String> browserProperties = new ArrayList<>();
    public static String binaryPath;

    @Override
    protected WebDriver getDriver() {
        WebDriver driver = getDriverInstance();
        logger.info("Browser User Agent: " + getUserAgent(driver));
        return driver;
    }

    protected WebDriver getDriverInstance() {
        if (binaryPath != null) {
            File file = new File(binaryPath);
            if (file.exists()) {
                logger.info("Using the following FireFox executable: " + file);
                FirefoxBinary firefoxBinary = new FirefoxBinary((file));
                return new FirefoxDriver(firefoxBinary, getProfile());
            }
        }
        logger.info("Using Static FireFox executable");
        return new FirefoxDriver(getProfile());
    }

    private FirefoxProfile getProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        List<String> properties = new ArrayList<>();
        properties.addAll(browserProperties);
        downloadDirectory = createDirectory("download");
        properties.add(String.format("browser.download.dir=%s", downloadDirectory));
        if (logger.isDebugEnabled()) {
            String debugDirectory = createDirectory("screenshots");
            properties.add("webdriver.log.file=" + debugDirectory + "/firefox_console");
        }

        if (properties != null) {
            for (String property : properties) {
                logger.info("Set Firefox Preference: " + property);
                if (property == null) continue;
                String lineRegex = "([^=]+)=(.+)$";
                Pattern p = Pattern.compile(lineRegex);
                Matcher m = p.matcher(property);
                m.find(0);
                String key = m.group(1).trim();
                String value = m.group(2).trim();
                if (value.matches("\\d+")) {
                    profile.setPreference(key, Integer.parseInt(value));
                } else if (value.matches("true|false")) {
                    profile.setPreference(key, Boolean.parseBoolean(value));
                } else {  //treat as string
                    profile.setPreference(key, value);
                }
            }
        }
        profile.setEnableNativeEvents(false);
        return profile;
    }

    private String createDirectory(String name) {
        File downloadDirectory = new File("target/" + name);
        if (!downloadDirectory.exists()) {
            logger.info("-->Creating directory: " + downloadDirectory.getAbsolutePath());
            boolean created = downloadDirectory.mkdir();
            if (created) {
                logger.info( String.format("Directory %s was created successfully", downloadDirectory));
            } else {
                logger.warn( String.format("Unable to create directory %s", downloadDirectory));
            }
        }
        return downloadDirectory.getAbsolutePath();
    }

}
