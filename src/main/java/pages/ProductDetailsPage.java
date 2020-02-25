package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.WithTimeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.time.temporal.ChronoUnit;

public class ProductDetailsPage extends Page {
    Logger logger = LogManager.getLogger(ProductDetailsPage.class.getName());

    @AndroidFindBy(xpath = "//*[@resource-id = 'bylineInfo']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement pageRef;

    @AndroidFindBy(xpath = "//*[@resource-id = 'a-autoid-8']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement buyNowButton;

    @AndroidFindBy(xpath = "//*[@resource-id = 'add-to-cart-button']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement addToCartButton;

    @AndroidFindBy(xpath = "//*[@resource-id = 'title_feature_div']")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement productHeading;

    @AndroidFindBy(xpath = "//*[contains(@resource-id, 'atfRedesign_priceblock_priceToPay']/android.widget.EditText")
    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    MobileElement priceTag;

    /**
     * Constructor to initialze page
     */
    public ProductDetailsPage() {
        super();
        pageRefObj = pageRef;
    }

    /**
     * Get Product Title
     * @return Name of the title
     */
    public String getProductTitle() {
        return getText(productHeading);
    }

    /**
     * Click Buy Now button
     */
    public void clickBuyNow() {
        logger.info("Cliicking on buy now button");
        if (clickButton(buyNowButton)) {
            logger.info("Clicked on buy now button");
        } else {
            Assert.fail("Failed to click on buy now button");
        }
    }

    /**
     * Click on add to cart
     */
    public void clickAddToCart() {
        logger.info("Clicking on add to cart button");
        if (clickButton(addToCartButton)) {
            logger.info("Clicked on add to cart button");
        } else {
            Assert.fail("Failed to click on add to cart button");
        }
    }

    /**
     * Get Price Tag in product details screen
     *
     * @return
     */
    public String getPriceTag() {
        verticalswipe(true, priceTag);
        return getText(priceTag).trim();
    }
}
