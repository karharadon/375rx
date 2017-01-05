package tests;

import helpers.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import pages.LoginPage;
import pages.MainPage;
import pages.ResultPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    protected MainPage mainPage = PageFactory.initElements(getWebDriver(), MainPage.class);
    protected ResultPage resultPage = PageFactory.initElements(getWebDriver(), ResultPage.class);
    private static WebDriver driver;
    private static String browser = ConfigProperties.getProperty("browser");

    public static WebDriver getWebDriver() {
        if (driver != null) {
            return driver;
        } else {
            if (browser.equals("chrome")) {
                driver = new ChromeDriver();
            }
            if (browser.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                        "/src/test/resources/drivers/geckodriver");
                driver = new FirefoxDriver();
            }
            if (browser.equals("internetExplorer")) {  //TODO install driver and check this option
                driver = new InternetExplorerDriver();
            }
            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("impWait")),
                        TimeUnit.SECONDS);
                driver.manage().window().maximize();
            }
            return driver;
        }
    }
}
