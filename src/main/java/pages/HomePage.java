package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.WithTimeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class HomePage extends Page {
    Logger logger = LogManager.getLogger(HomePage.class.getName());

    @AndroidFindBy(xpath = "//*[@content-desc = 'Home']")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement pageRef;

    @AndroidFindBy(id = "rs_search_src_text")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement searchBar;

    @AndroidFindBy(id = "chrome_action_bar_burger_icon")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement hamburgerMenu;

    @AndroidFindBy(id = "item_title")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    List<MobileElement> searchResults;

    @AndroidFindBy(xpath = "//*[@id = 'item_title']//*[@resource-id = 'rs_results_styled_price_v2']/android.widget.TextView")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    MobileElement priceTag;

    @AndroidFindBy(id = "chrome_action_bar_cart_image")
    @WithTimeout(time = 5, chronoUnit = ChronoUnit.SECONDS)
    public MobileElement cartIcon;

    /**
     * Constructor to initialze page
     */
    public HomePage() {
        super();
        pageRefObj = pageRef;
    }

    /**
     * Open Hamburger Menu
     *
     */
    public void openHamburgerMenu() {
        if(hamburgerMenu.isDisplayed()) {
            logger.info("Hamburger menu not opened. Opening It");
            if (clickButton(hamburgerMenu)) {
                logger.info("Clicked open hamburger menu");
            } else {
                Assert.fail("Failed to opem hamburger menu");
            }
        } else {
            logger.info("Hamburger menu already open");
        }
    }

    /**
     * Click Cart Icon
     */
    public void clickCart() {
        logger.info("Clicking on cart Icon");
        if (clickButton(cartIcon)) {
            logger.info("Clicked on cart Icon");
        } else {
            Assert.fail("Failed to click cart icon");
        }
    }

    /**
     * Search using search bar
     */
    public void searchOnHomePage (String searchKeywords) {
        logger.info("Searching for " + searchKeywords);
        if (!setText(searchBar, searchKeywords)) {
            logger.info("Failed to search");
            Assert.fail("Failed to search");
        } else {
            searchBar.sendKeys(Keys.ENTER);
        }
    }

    /**
     * Click on search options
     * @param optionsContains
     * @return Name of the product
     */
    public String clickOnSearchOptions (String optionsContains) {
        List<MobileElement> elements = getMobileElements(searchResults);
        for (MobileElement element : elements) {
            if (getText(element).contains(optionsContains)) {
                element.click();
                return getText(element);
            }
            else {
                Assert.fail("Failed to get options");
            }
        }
        return null;
    }

    /**
     * Get Price Tag
     * @param selectedItemName
     * @return Price Tag
     */
    public String getPriceOfTheSelectedItem(String selectedItemName) {
        List<MobileElement> elements = getMobileElements(searchResults);
        for (MobileElement element : elements) {
            if (getText(element).equalsIgnoreCase(selectedItemName)) {
                return getText(priceTag).trim().split(" ")[0];
            }
        }
        return null;
    }
}
