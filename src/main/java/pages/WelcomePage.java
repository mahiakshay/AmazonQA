package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.WithTimeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.time.temporal.ChronoUnit;

public class WelcomePage extends Page {
    Logger logger = LogManager.getLogger(WelcomePage.class.getName());

    @AndroidFindBy(xpath = "//*[@text = 'Welcome']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement pageRef;

    @AndroidFindBy(xpath = "//*[@resource-id = 'ap_email_login']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement userNameTextField;

    @AndroidFindBy(xpath = "//*[@resource-id = 'register_accordion_header']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement createAccountRadioButton;

    @AndroidFindBy(xpath = "//*[@resource-id = 'login_accordion_header']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement signInRadioButton;

    @AndroidFindBy(xpath = "//*[@resource-id = 'continue']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement continueButton;

    @AndroidFindBy(xpath = "//*[@text = 'Need help?']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement needHelpLink;

    /**
     * Constructor to call super calls
     */
    public WelcomePage() {
        super();
        pageRefObj = pageRef;
    }

    /**
     * Enter Email ID or Phone Number
     *
     * @param emailOrPhone User Email ID or Phone Number
     */
    public void enterEmail(String emailOrPhone) {
        logger.info("Entering email ID or Phone Number");
        if (!setText(userNameTextField, emailOrPhone)) {
            Assert.fail("Failed to enter email ID or phone number");
        } else {
            logger.info("Entered email ID or phone number successfully");
        }
    }

    /**
     * Click Continue Button
     */
    public void clickContinue() {
        logger.info("Clicking on Continue Button");
        if (!clickButton(continueButton)) {
            Assert.fail("Failed to click continue button");
        } else {
            logger.info("Clicked on continue button");
        }
    }
}

