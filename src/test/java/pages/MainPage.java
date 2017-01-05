package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.ConfigProperties.getProperty;
import static tests.BaseTest.getWebDriver;

public class MainPage extends AbstractPage {
    private String title = getProperty("title");
    private String keywords = getProperty("keywords");
    private String lastName = getProperty("lastName");
    private String country = getProperty("country");
    private int startFromThisPage = Integer.parseInt(getProperty("startFromThisPage"));

    public void advancedSearch() {
        waitButtonAndClick(buttonGoToAdvancedSearch);
        if (startFromThisPage == 1) {
            activateField(fieldKeywords, keywords);
            activateField(fieldLastname, lastName);
            activateField(fieldTitle, title);
            if (!title.equals("null")) {
                activateDD(fieldTitleScope, "Current");
            }
            if (!country.equals("null")) {
                activateDD(fieldLocation, "Located in or near:");
            }
            activateDD(fieldCountry, country);
            activateCheckBox(buttonIndustry);
            activateCheckBox(checkboxIT);
            activateCheckBox(checkboxInternet);
            activateCheckBox(checkboxSoftWare);
            activateCheckBox(checkbox2LevelContacts);
            activateCheckBox(checkboxGroups);
            clickButonWithJS(buttonAdvancedSearch);
        } else {
            getWebDriver().get("https://www.linkedin.com/vsearch/p?" + keywords() + lastName() + title() +
                    "openAdvancedForm=true&" + titleScope() +
                    "locationType=I&countryCode=ua&f_I=6,4,96&rsid=1542012251482615949571&orig=MDYS&page_num=" +
                    startFromThisPage + "&pt=people&f_N=S,A");
        }
    }

    private String titleScope() {
        if (!title.equals("null")) {
            return "titleScope=C&";
        } else {
            return "";
        }
    }

    private String title() {
        if (!title.equals("null")) {
            return "title=" + title + "&";
        } else {
            return "";
        }
    }

    private String lastName() {
        if (!lastName.equals("null")) {
            return "lastName=" + lastName + "&";
        } else {
            return "";
        }
    }

    private String keywords() {
        if (!keywords.equals("null")) {
            return "keywords=" + keywords + "&";
        } else {
            return "";
        }
    }

    private void activateDD(WebElement element, String property) {
        if (!property.equals("null")) {
            selectFromDD(element, property);
            System.out.println(property + " was putted in DD");
        }
    }

    private void activateCheckBox(WebElement element) {
        clickButonWithJS(element);
    }

    private void activateField(WebElement element, String property) {
        if (!property.equals("null")) {
            clearAndSendKeys(element, property);
            System.out.println(property + " was putted in field");
        }
    }

    @FindBy(css = "#advanced-search")
    private WebElement buttonGoToAdvancedSearch;

    @FindBy(css = "#advs-keywords")
    private WebElement fieldKeywords;

    @FindBy(css = "#advs-title")
    private WebElement fieldTitle;

    @FindBy(css = "#advs-titleScope")
    private WebElement fieldTitleScope;

    @FindBy(css = "#advs-lastName")
    private WebElement fieldLastname;

    @FindBy(css = "#advs-locationType")
    private WebElement fieldLocation;

    @FindBy(css = "#advs-countryCode")
    private WebElement fieldCountry;

    @FindBy(css = "li#adv-facet-I button.facet-toggle ")
    private WebElement buttonIndustry;

    @FindBy(css = "input#adv-96-I-ffs")
    private WebElement checkboxIT;

    @FindBy(css = "input#adv-4-I-ffs")
    private WebElement checkboxSoftWare;

    @FindBy(css = "input#adv-6-I-ffs")
    private WebElement checkboxInternet;

    @FindBy(css = "#adv-S-N-ffs")
    private WebElement checkbox2LevelContacts;

    @FindBy(css = "#adv-A-N-ffs")
    private WebElement checkboxGroups;

    @FindBy(css = ".submit-advs")
    private WebElement buttonAdvancedSearch;
}
