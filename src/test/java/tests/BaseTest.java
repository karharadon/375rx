package tests;

import helpers.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by lastochkin on 12/16/16.
 */
public class BaseTest {
    private static WebDriver driver;
    private static String browser = ConfigProperties.getProperty("browser");

    public static WebDriver getWebDriver() {
        if (driver != null) {
            return driver;}
        else {
            if (browser.equals("chrome")) {
                driver = new ChromeDriver();
            }
            if (browser.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "/home/lastochkin/geckodriver");
                driver = new FirefoxDriver();
            }
            if (browser.equals("internetExplorer")) {
                driver = new InternetExplorerDriver();
            }
            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("impWait")),
                        TimeUnit.SECONDS);
                driver.manage().window().maximize();
            }
            return driver;
        }
}}
