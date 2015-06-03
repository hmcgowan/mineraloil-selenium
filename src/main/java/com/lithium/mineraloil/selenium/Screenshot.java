package com.lithium.mineraloil.selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Screenshot {

    private static final Logger logger = LoggerFactory.getLogger(Screenshot.class);
    private static final String screenShotDirectory = getDirectory();

    public static void takeScreenShot(WebDriver driver, String filename) {
        try {
            filename +=  "_" + System.currentTimeMillis() + "_" + Thread.currentThread().getId() + ".png";
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            logger.info("Creating Screenshot: " + screenShotDirectory + filename);
            FileUtils.copyFile(scrFile, new File(screenShotDirectory + filename));
        } catch (IOException | UnreachableBrowserException e) {
            logger.error(" Unable to take screenshot: " + e.toString());
        }
    }

    public static void takeScreenShot(String filename) {
        if (logger.isDebugEnabled()) {
            takeFullDesktopScreenshot(filename);
        } else {
            if (DriverManager.isDriverStarted()) {
                takeScreenShot(DriverManager.getCurrentWebDriver(), filename);
            } else {
                logger.error("Webdriver not started. Unable to take screenshot");
            }
        }
    }

    public static void takeFullDesktopScreenshot(String filename) {
        try {
            filename +=  "_" + System.currentTimeMillis() + "_" + Thread.currentThread().getId() + ".png";
            BufferedImage img = getScreenAsBufferedImage();
            File output = new File(filename);
            ImageIO.write(img, "png", output);
            logger.info("Creating FULL SCREEN Screenshot: " + screenShotDirectory + filename);
            FileUtils.copyFile(output, new File(screenShotDirectory + filename));
            FileUtils.deleteQuietly(output);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    private static BufferedImage getScreenAsBufferedImage() {
        BufferedImage img = null;
        try {
            Robot r;
            r = new Robot();
            Toolkit t = Toolkit.getDefaultToolkit();
            Rectangle rect = new Rectangle(t.getScreenSize());
            img = r.createScreenCapture(rect);
        } catch (AWTException e) {
            logger.error(e.toString());
        }
        return img;
    }



    private static String getDirectory() {
        String screenshotDirectory = ClassLoader.getSystemClassLoader().getSystemResource("").getPath() + "../screenshots/";
        File file = new File(screenshotDirectory);
        if (!file.exists()) file.mkdir();
        logger.info("Creating screenshot directory: " + screenshotDirectory);
        return screenshotDirectory;
    }

}
