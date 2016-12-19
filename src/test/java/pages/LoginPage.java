package pages;

import helpers.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.ConfigProperties.getProperty;

/**
 * Created by lastochkin on 12/16/16.
 */
public class LoginPage extends AbstractPage {
  /*  private  WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
 */
    public void login(WebDriver driver){
        driver.get(getProperty("url"));
        clearAndSendKeys(fieldLogin, getProperty("login"));
        clearAndSendKeys(fieldPassword, getProperty("password"));
        waitButtonAndClick(buttonLogIn);

        //return PageFactory.initElements(driver, MainPage.class);
    }

    @FindBy(css = "#login-email")
    private WebElement fieldLogin;

    @FindBy(css = "#login-password")
    private WebElement fieldPassword;

    @FindBy(css = "#login-submit")
    private WebElement buttonLogIn;


}
