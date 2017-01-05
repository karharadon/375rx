package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.ConfigProperties.getProperty;

public class LoginPage extends AbstractPage {
    public void login(WebDriver driver){
        driver.get(getProperty("url"));
        clearAndSendKeys(fieldLogin, getProperty("login"));
        clearAndSendKeys(fieldPassword, getProperty("password"));
        waitButtonAndClick(buttonLogIn);
    }

    @FindBy(css = "#login-email")
    WebElement fieldLogin;

    @FindBy(css = "#login-password")
    WebElement fieldPassword;

    @FindBy(css = "#login-submit")
    WebElement buttonLogIn;
}
