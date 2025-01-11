package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserFactory;

import java.time.Duration;

public class BasePage {
    public WebDriverWait wait = new WebDriverWait(BrowserFactory.getDriver(), Duration.ofSeconds(10));

    public void visibilityOfElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitAndClick(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void clickAndType(WebElement element, String text){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        element.sendKeys(text);
    }
}
