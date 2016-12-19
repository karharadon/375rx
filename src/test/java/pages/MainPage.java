package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.ConfigProperties.getProperty;

/**
 * Created by lastochkin on 12/16/16.
 */
public class MainPage extends AbstractPage{

    public void advancedSearch(){
        waitButtonAndClick(buttonGoToAdvancedSearch);
        clearAndSendKeys(fieldTitle,getProperty("title"));
        selectFromDD(fieldTitleScope,getProperty("titleScope"));
        selectFromDD(fieldLocation,getProperty("location"));
        selectFromDD(fieldCountry,getProperty("country"));
        clickButonWithJS(buttonIndustry);
        clickButonWithJS(checkboxSoftWare);
        waitButtonAndClick(buttonAdvancedSearch);
    }

    @FindBy(css = "#advanced-search")
    private WebElement buttonGoToAdvancedSearch;

    @FindBy(css = "#advs-title")
    private WebElement fieldTitle;

    @FindBy(css = "#advs-titleScope")
    private WebElement fieldTitleScope;

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

    @FindBy(css = ".submit-advs")
    private WebElement buttonAdvancedSearch;
}
