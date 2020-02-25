package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.WithTimeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.time.temporal.ChronoUnit;

public class SignInPage extends Page {
    Logger logger = LogManager.getLogger(SignIntoYourAccountPage.class.getName());

    @AndroidFindBy(xpath = "//*[@text = 'Sign-In']")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement pageRef;

    @AndroidFindBy(xpath = "//*[@resource-id = 'ap_password']")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement passwordTextField;

    @AndroidFindBy(xpath = "//*[@resource-id = 'signInSubmit']")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement signInButton;

    @AndroidFindBy(xpath = "//*[@resource-id = 'auth-signin-show-password-checkbox']")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement showPassword;

    @AndroidFindBy(xpath = "//*[contains(@text, 'Keep me signed in')]")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement keepMeSignedIn;

    public SignInPage() {
        super();
        pageRefObj = pageRef;
    }

    /**
     * Enter Password
     *
     * @param password Password to be entered
     */
    public void enterPassword(String password) {
        logger.info("Entering Password");
        if(!setText(passwordTextField, password)) {
            Assert.fail("Failed to enter password");
        } else {
            logger.info("Entered Password successfully");
        }
    }

    /**
     * Click Sign In
     */
    public void clickSignIn() {
        logger.info("Clicking on Sign In");
        if(!clickButton(signInButton)) {
            Assert.fail("Failed to click on sign in  buttom");
        } else {
            logger.info("Clicked on sign in button");
        }
    }
}
