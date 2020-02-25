package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.WithTimeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.time.temporal.ChronoUnit;

public class SelectAPaymentMethodPage extends Page{
    Logger logger = LogManager.getLogger(SelectAPaymentMethodPage.class.getName());

    @AndroidFindBy(xpath = "//*[@text = 'Select a payment method']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement pageRef;

    @AndroidFindBy(xpath = "//*[@text = 'Continue']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement continueButton;

    /**
     * Constructor to initialze page
     */
    public SelectAPaymentMethodPage() {
        super();
        pageRefObj = pageRef;
    }

    /**
     * Click Continue Button
     */
    public void clickContinue() {
        logger.info("Cliicking on continue button");
        if (clickButton(continueButton)) {
            logger.info("Clicked on continue button");
        } else {
            Assert.fail("Failed to click on continue button");
        }
    }
}
