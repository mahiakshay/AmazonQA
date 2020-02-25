package AmazonQATests;

import Base.BaseTest;
import Base.TestGroups;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SignIntoYourAccountPage;
import pages.WelcomePage;
import pages.SignInPage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ChooseYourDeliveryOptionsPage;
import pages.SelectAPaymentMethodPage;

public class Test1 extends BaseTest {
    Logger logger = LogManager.getLogger(Test.class.getName());
    SignIntoYourAccountPage signIntoYourAccountPage;
    WelcomePage welcomePage;
    SignInPage signInPage;
    HomePage homePage;
    ProductDetailsPage productDetailsPage;
    ChooseYourDeliveryOptionsPage chooseYourDeliveryOptionsPage;
    SelectAPaymentMethodPage selectAPaymentMethodPage;

    @BeforeTest(groups = {TestGroups.RREGRESSION, TestGroups.SANITY, TestGroups.SMOKE})
    public void beforeTest(ITestContext context) {
        super.beforeTest(context);
    }

    @Test(testName = "“Login to amazon mobile application and search for\n" +
            "an item and add to cart and purchase it",
            description = "Search for a 65-inch TV and purchase a random\n" +
                    "(not the first/last) TV from the search result",
            priority = 1)
    public void loginAndSearchForAProduct() {
        signIntoYourAccountPage = new SignIntoYourAccountPage();
        Assert.assertTrue(signIntoYourAccountPage.isValid(), "Sign In to your account page is not valid");
        signIntoYourAccountPage.clickOnSignIn();
        welcomePage = new WelcomePage();
        welcomePage.waitForLoadingPage();
        Assert.assertTrue(welcomePage.isValid(), "Welcome page is not valid");
        welcomePage.enterEmail(userName);
        welcomePage.clickContinue();
        signInPage = new SignInPage();
        signInPage.waitForLoadingPage();
        Assert.assertTrue(welcomePage.isValid(), "Welcome page is not valid");
        signInPage.enterPassword(password);
        signInPage.clickSignIn();
        homePage = new HomePage();
        homePage.waitForLoadingPage();
        if (homePage.isValid()) {
            logger.info("Home Page is valid");
        } else {
            Assert.fail("Navigation to home page failed");
        }
        homePage.searchOnHomePage("65-inch TV");
        homePage.verticalswipe(true, homePage.cartIcon);
        String nameOnSearchPage = homePage.clickOnSearchOptions("Samsung");
        String priceOnSearchPage = homePage.getPriceOfTheSelectedItem(nameOnSearchPage);
        productDetailsPage = new ProductDetailsPage();
        productDetailsPage.waitForLoadingPage();
        Assert.assertTrue(productDetailsPage.isValid(), "Product detail page is valid");
        String nameOnProductDetailScreen = productDetailsPage.getProductTitle();
        Assert.assertEquals(nameOnProductDetailScreen, nameOnSearchPage, "Names on search and product details screens don't match");
        String priceOnProductDetailScreen = productDetailsPage.getPriceTag();
        Assert.assertEquals(priceOnProductDetailScreen, priceOnSearchPage, "Price on search and product details screens don't match");
        chooseYourDeliveryOptionsPage = new ChooseYourDeliveryOptionsPage();
        Assert.assertEquals(chooseYourDeliveryOptionsPage.isValid(), "Choose your delivery options page not valid");
        chooseYourDeliveryOptionsPage.clickContinue();
        selectAPaymentMethodPage = new SelectAPaymentMethodPage();
        Assert.assertEquals(selectAPaymentMethodPage.isValid(), "Select a payemnt method page not valid");
        selectAPaymentMethodPage.clickContinue();
    }
}
