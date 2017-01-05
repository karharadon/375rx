package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.ConfigProperties.getProperty;
import static tests.BaseTest.getWebDriver;

class AbstractPage {

    private final int waitWebElem = Integer.parseInt(getProperty("waitWebElem"));

    void clearAndSendKeys(WebElement webElement, String text) {
        waitWhenClickable(webElement).clear();
        webElement.sendKeys(text);
    }

    void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    WebElement waitWhenClickable(WebElement element) {
        try {
            new WebDriverWait(getWebDriver(), waitWebElem).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't click webelement" + element);
        }
        return element;
    }

    void waitButtonAndClick(WebElement element) {
        waitWhenClickable(element);
        element.click();
    }

    void selectFromDD(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    void clickButonWithJS(WebElement elem) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click();", elem);
    }
}
