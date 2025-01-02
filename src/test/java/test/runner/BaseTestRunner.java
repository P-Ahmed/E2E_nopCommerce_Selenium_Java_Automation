package test.runner;

import org.testng.annotations.BeforeTest;
import utils.BrowserFactory;

public class BaseTestRunner {
    @BeforeTest
    public void setupBrowser() {
        BrowserFactory.getDriver().get("https://demo.nopcommerce.com/");
    }

    public void tearDown() {
        BrowserFactory.quitDriver();
    }
}
