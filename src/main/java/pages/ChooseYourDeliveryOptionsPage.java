package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.WithTimeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.time.temporal.ChronoUnit;

public class ChooseYourDeliveryOptionsPage extends Page{
    Logger logger = LogManager.getLogger(ChooseYourDeliveryOptionsPage.class.getName());

    @AndroidFindBy(xpath = "//*[@text = 'Choose your delivery options']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement pageRef;

    @AndroidFindBy(xpath = "//*[@text = 'Continue']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement continueButton;

    /**
     * Constructor to initialze page
     */
    public ChooseYourDeliveryOptionsPage() {
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
