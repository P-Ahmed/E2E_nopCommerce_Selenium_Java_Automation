package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BrowserFactory {
    public WebDriver driver;

    @BeforeTest
    public void browserSetup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @AfterClass
    public void quitBrowser(){
        driver.quit();
    }
}
