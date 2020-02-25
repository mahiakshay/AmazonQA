package Base;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import pages.AppiumManager;


public class BaseTest {
    protected static String userName;
    protected static String password;
    AppiumManager appiumManager;

    /**
     * Before suite for SUT
     * Start Appium Server and Launch App
     *
     * @param context System Under Test
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        appiumManager = new AppiumManager();
        appiumManager.startAppiumServer();
        AppiumManager.launchApp();
    }

    /**
     * AfterSuite for SUT
     * Quit driver and Stop Appium Server
     *
     * @param context
     */
    @AfterSuite(alwaysRun = true)
    public void afterSuite(ITestContext context) {
        AppiumManager.getDriver().quit();
        appiumManager.stopServer();
    }

    /**
     * Before Test for SUT
     * Get userName and Password
     *
     * @param context SUT
     */
    @BeforeTest(alwaysRun = true)
    public void beforeTest(ITestContext context) {
        beforeSuite(context);
        userName = getParam(context, "username");
        password = getParam(context, "password");
    }

    /**
     * Get Params from Current textng.xml
     *
     * @param context SUT
     * @param param   Parameters
     * @return XML parameters
     */
    public String getParam(ITestContext context, String param) {
        String temp = context.getCurrentXmlTest().getParameter(param);
        return temp == null ? "" : temp;
    }
}