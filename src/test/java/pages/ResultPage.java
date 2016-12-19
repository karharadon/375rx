package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static tests.BaseTest.getWebDriver;

/**
 * Created by lastochkin on 12/17/16.
 */
public class ResultPage extends AbstractPage {

    private int pagesAmount() {
        String profilesString = resultAmount.getText();
        String p = null;
        if (profilesString.contains(",")) {
            p = profilesString.replaceAll(",", ".");
            System.out.println(p);
        }
        double profiles = Double.parseDouble(p) * 1000;
        double pagesAmount;
        if (profiles % 10 == 0) {
            pagesAmount = profiles / 10;
        } else {
            pagesAmount = (profiles / 10) + 1;
        }
        return (int) pagesAmount;
    }

    private void navigateBack() {
        getWebDriver().navigate().back();
        waitForPageLoad(getWebDriver());
    }

    public void addConnects() {
        getWebDriver().get("https://www.linkedin.com/vsearch/p?title=project%20manager&openAdvancedForm=true&titleScope=C&locationType=I&countryCode=ua&f_I=4&rsid=1542012251482184707324&orig=ADVS&page_num=38&pt=people");
        waitForPageLoad(getWebDriver());
        if (waitWhenClickable(strong).isDisplayed()) {
            for (int page = 39; page <= pagesAmount(); page++) {
                for (WebElement actionButton : actionButtons) {
                    if (actionButton.getText().equals("Connect")) {
                        try {
                            waitButtonAndClick(actionButton);
                        } catch (StaleElementReferenceException e) {
                            try {
                                waitButtonAndClick(actionButton);
                            } catch (StaleElementReferenceException e1) {
                                if (buttonInvite.isDisplayed()) {
                                    navigateBack();
                                }else{
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                }
                WebElement nextPage = getWebDriver().findElement(By.cssSelector("a.page-link[title='Page " + page +
                        "']"));
                clickButonWithJS(waitWhenClickable(nextPage));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else {
            System.out.println("Preferences for advanced search are not active");
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
