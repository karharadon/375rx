package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static helpers.ConfigProperties.getProperty;
import static tests.BaseTest.getWebDriver;

/**
 * Created by lastochkin on 12/17/16.
 */
public class ResultPage extends AbstractPage {
    public void addConnects() {
        int startFromThisPage = Integer.parseInt(getProperty("startFromThisPage"));
        boolean isActualResults = waitWhenClickable(strong).isDisplayed();
        waitForPageLoad(getWebDriver());
        if (isActualResults) {
            System.out.println("Pages amount = " + pagesAmount());
            for (int currentPage = startFromThisPage; currentPage <= pagesAmount(); currentPage++) {
                System.out.println("Current page:"+ currentPage);
                WebElement nextPage = null;
                if (currentPage < pagesAmount()) {
                    nextPage = getWebDriver().findElement(By.cssSelector("a.page-link[title='Page " + (currentPage + 1)
                            + "']"));
                }
                clickAllAddingButtons();
                if (currentPage < pagesAmount()) {
                    clickButonWithJS(waitWhenClickable(nextPage));
                    threadSleep(3000);
                }
            }
        } else {
            System.out.println("Preferences for advanced search are not active");
        }
    }

    private int pagesAmount() {
        String profilesString = resultAmount.getText();
        String p;
        int profilesAmount;
        int pagesAmount;

        if (profilesString.contains(",")) {
            p = profilesString.replaceAll(",", ".");
            profilesAmount = (int) Double.parseDouble(p) * 1000;
        } else {
            profilesAmount = Integer.parseInt(profilesString);
        }

        if (profilesAmount % 10 == 0) {
            pagesAmount = profilesAmount / 10;
        } else {
            pagesAmount = (profilesAmount / 10) + 1;
        }
        return pagesAmount;
    }

    private void navigateBack() {
        getWebDriver().navigate().back();
        waitForPageLoad(getWebDriver());
        System.out.println("Navigate back!");
    }

    private void clickAllAddingButtons() {
        for (WebElement actionButton : actionButtons) {
            try {
                if (actionButton.getText().equals("Connect")) {
                    waitButtonAndClick(actionButton);
                    System.out.println("Blue button was clicked.");
                }
            } catch (StaleElementReferenceException e) {
                if (buttonInvite.isDisplayed()) {
                    //TODO
                } else {
                    System.out.println("Can't click" + actionButton + ". StaleElementReferenceException");
                    e.printStackTrace();
                }
            }
        }
    }

    private void threadSleep(int milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FindAll(@FindBy(how = How.CSS, using = "div.srp-actions.blue-button>a"))
    private List<WebElement> actionButtons;

    @FindBy(how = How.XPATH, using = "//*[@id=\"search-types\"]/div/ul/li[2]/strong")
    private WebElement strong;

    @FindBy(how = How.CSS, using = "a[title='Next Page']")
    private WebElement buttonNext;

    @FindBy(how = How.CSS, using = "input.btn-primary")
    private WebElement buttonInvite;

    @FindBy(how = How.CSS, using = "div.search-info>p>strong")
    private WebElement resultAmount;
}
