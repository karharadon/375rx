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
        int profiles = (Integer.parseInt(resultAmount.getText()));
        int pagesAmount;
        if (profiles % 10 == 0) {
            pagesAmount = profiles / 10;
        } else {
            pagesAmount = (profiles / 10) + 1;
        }
        return pagesAmount;
    }

    public void addConnects() {
        //  getWebDriver().get("https://www.linkedin.com/vsearch/p?title=recruiter&openAdvancedForm=true&titleScope=C&locationType=I&countryCode=ua&f_I=96&rsid=1542012251482149029842&orig=ADVS&page_num=10&pt=people&openFacets=N,G,CC,I");
        if (waitWhenClickable(strong).isDisplayed()) {
            for (int page = 10; page <= pagesAmount(); page++) {
                for (WebElement actionButton : actionButtons) {
                    if (actionButton.getText().equals("Connect")) {
                        try {
                            waitButtonAndClick(actionButton);
                            try {
                                if (buttonInvite.isDisplayed()) {
                                    getWebDriver().navigate().back();
                                    waitForPageLoad(getWebDriver());
                                }
                            } catch (NoSuchElementException e) {
                                //it should throws NoSuchElementExeption
                            }
                        } catch (StaleElementReferenceException e) {
                            System.out.println("Stale elemen exeption was caught");
                            waitButtonAndClick(actionButton);
                        }
                    }
                }
                WebElement nextPage = getWebDriver().findElement(By.cssSelector("a.page-link[title='Page " + page +
                        "']"));
                clickButonWithJS(waitWhenClickable(nextPage));
                try {
                    Thread.sleep(2000);
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
