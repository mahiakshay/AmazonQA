package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.WithTimeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.time.temporal.ChronoUnit;

public class SignIntoYourAccountPage extends Page {
    Logger logger = LogManager.getLogger(SignIntoYourAccountPage.class.getName());

    @AndroidFindBy(id = "signin_to_yourAccount")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement pageRef;

    @AndroidFindBy(id = "sign_in_button")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement signInButton;

    @AndroidFindBy(id = "new_user")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement newUserButton;

    @AndroidFindBy(id = "skip_sign_in_button")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement skipSignInButton;

    public SignIntoYourAccountPage() {
        super();
        pageRefObj = pageRef;
    }

    /**
     * Click on Sign In button
     */
    public void clickOnSignIn() {
        logger.info("Clicking on Sign In");
        if(clickButton(signInButton)){
            logger.error("Failed to click on sign in button");
            Assert.fail("Failed to click on Sign in button");
        } else {
            logger.info("Clicked on Sign In Button");
        }
    }

    /**
     * Click on Sign In button
     */
    public void clickOnNewUser() {
        logger.info("Clicking on Sign In");
        if(clickButton(newUserButton)){
            logger.error("Failed to click on New User button");
            Assert.fail("Failed to click on New User button");
        } else {
            logger.info("Clicked on new user Button");
        }
    }

    /**
     * Click on Sign In button
     */
    public void clickOnSkipSignIn() {
        logger.info("Clicking on Sign In");
        if(clickButton(signInButton)){
            logger.error("Failed to click on skip sign in button");
            Assert.fail("Failed to click on skip Sign in button");
        } else {
            logger.info("Clicked on skip Sign In Button");
        }
    }
}
