package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

/**
 * Base class for an Appium Application Page.
 */
public class Page extends AppiumManager{
    Logger logger = LogManager.getLogger(Page.class.getName());
    protected MobileElement pageRefObj;

    /**
     * Init Page Factory
     */
    public Page(){
        PageFactory.initElements(new AppiumFieldDecorator(AppiumManager.getDriver(), Duration.ofSeconds(20)), this);
    }

    /**
     * Click on a button located on the page.
     *
     * @param button - the button object to select.
     * @return true if the operation was performed, false otherwise.
     */
    public boolean clickButton(MobileElement button) {
        try {
            if (!button.isEnabled()) {
                logger.error("Unable to click on button as its not enabled/displayed");
                return false;
            }
            button.click();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return true;
    }

    /**
     * gets text of an element.
     *
     * @param elem mobile element
     * @return string value
     */
    public String getText(MobileElement elem) {
        try {
            MobileElement element = getMobileElement(elem);
            return (element != null) ? element.getText() : "";

        } catch (Exception e) {
            return "";
        }
    }

    /**
     *Method to  get the mobile element
     * @param mobileElement
     * @return mobileElement
     */
    public MobileElement getMobileElement(MobileElement mobileElement) {
        try {
            return (mobileElement != null && mobileElement.isDisplayed()) ? mobileElement : null;
        } catch (StaleElementReferenceException se) {
            return (mobileElement != null && mobileElement.isDisplayed()) ? mobileElement : null;
        } catch (WebDriverException e) {
            logger.info("Failed to getMobileElement : " + e.getMessage());
            return null;
        }
    }

    /**
     * Find all elements
     * @param mobileElement
     * @return all elements
     */
    public List<MobileElement> getMobileElements(List<MobileElement> mobileElement) {
        if (!(mobileElement.size() == 0 || mobileElement == null)){
            return  mobileElement;
        } else {
            logger.info("Failed to getMobileElements");
            return null;
        }
    }

    /**
     * page valid?.
     *
     * @return true or false
     */
    public boolean isValid() {
        MobileElement element = pageRefObj;
        try {
            return (element != null && element.isDisplayed());
        } catch (StaleElementReferenceException se) {
            return (element != null && element.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * sets value to element.
     *
     * @param elem mobile element (textbox)
     * @param val  value to be set.
     * @return true or false
     */
    public boolean setText(MobileElement elem, String val) {
        MobileElement element = getMobileElement(elem);
        if (element != null && element.isEnabled()) {
            element.sendKeys(val);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to scroll up and down
     *
     * @param bottomToTop
     * @param element
     */
    public void verticalswipe(boolean bottomToTop, MobileElement element) {
        if (bottomToTop) {
            int startYCordinate = element.getCenter().getY();
            startYCordinate = startYCordinate / 2;
            int endYCordinate = element.getCenter().getY();
            Verticalswipe(endYCordinate, startYCordinate, 500, 2000);
        } else {
            int startYCordinate = element.getCenter().getY();
            startYCordinate = startYCordinate / 2;
            int endYCordinate = element.getCenter().getY();
            Verticalswipe(startYCordinate, endYCordinate, 500, 2000);
        }
    }

    //method to swipe up or down based on the parameters
    public void Verticalswipe(int startPoint, int endPoint, int anchor, int duration) {
        TouchAction action = new TouchAction(AppiumManager.getDriver());
        action.press(new PointOption().withCoordinates(anchor, startPoint))
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(duration)))
                .moveTo(new PointOption().withCoordinates(anchor, endPoint))
                .release()
                .perform();

    }

    /**
     * Wait for loading page.
     *
     * @return true if page loaded.
     */
    public boolean waitForLoadingPage() {
        logger.info("waiting for action bar");
        if (!isValid()) {
            logger.error("Action bar is not found");
            return false;
        }
        return true;
    }
}
