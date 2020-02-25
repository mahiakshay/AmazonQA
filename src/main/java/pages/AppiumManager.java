package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class AppiumManager {
    Logger logger = LogManager.getLogger(AppiumManager.class.getName());
    private static AppiumDriver driver;
    private static Properties properties;
    FileInputStream fileInputStream;
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;

    /**
     * Constructor to load application.properties file
     *
     */
    public AppiumManager() {
        properties = new Properties();
        try {
        File file = new File("application.properties");
        fileInputStream = new FileInputStream(file.getAbsolutePath());
        }  catch (FileNotFoundException e) {
            logger.error("Could get the application.properties file");
            e.printStackTrace();
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error("Failed to load properties file");
            e.printStackTrace();
        }
    }

    /**
     * Get Appium Driver
     * @return driver
     */
    public static AppiumDriver getDriver() {
        return driver;
    }

    /**
     * Set Appium Driver
     * @param driver
     */
    public static void setDriver(AppiumDriver driver) {
        AppiumManager.driver = driver;
    }

    /**
     * Lauch app with desired caps
     */
    public static void launchApp() {
        try {
            setDriver(new AndroidDriver(new URL(properties.getProperty("URL")), serverCapabilities()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set Desired Caps
     * @return
     */
    public static DesiredCapabilities serverCapabilities() {
        DesiredCapabilities serverCapabilities = new DesiredCapabilities();
        serverCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        serverCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        serverCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, properties.getProperty("OS"));
        serverCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, properties.getProperty("appPackage"));
        serverCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, properties.getProperty("appActivity"));
        serverCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("DEVICENAME"));
        serverCapabilities.setCapability(MobileCapabilityType.UDID, properties.getProperty("UID"));
        serverCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        serverCapabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        return serverCapabilities;
    }

    /**
     * Start Appium Server
     */
    public void startAppiumServer() {
        logger.info("Starting Appium Server on: " + properties.getProperty("URL"));
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    /**
     * Stop Appium Server
     */
    public void stopServer() {
        if (service.isRunning()){
            logger.info("Appium Server is running. Stopping it");
            service.stop();
        } else {
            logger.info("Appium Server is already stopped");
        }
    }
}
