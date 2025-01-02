package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BrowserFactory {
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private BrowserFactory() {
    }

    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            initializeDriver();
        }
        return threadLocalDriver.get();
    }

    private static void initializeDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase(); // Default to Chrome
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                threadLocalDriver.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--width=1920", "--height=1080");
                threadLocalDriver.set(new FirefoxDriver(firefoxOptions));
                break;
            case "edge":
                threadLocalDriver.set(new EdgeDriver());
                break;
            case "safari":
                threadLocalDriver.set(new SafariDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        threadLocalDriver.get().manage().window().maximize();
        threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public static void quitDriver() {
        if (threadLocalDriver.get() != null) {
            threadLocalDriver.get().quit();
            threadLocalDriver.remove();
        }
    }
}
