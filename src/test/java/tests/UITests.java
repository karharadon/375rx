package tests;

import org.testng.annotations.Test;

public class UITests extends BaseTest {
    @Test
    public void addNewContacts() {
        loginPage.login(getWebDriver());
        mainPage.advancedSearch();
        resultPage.addConnects();
    }
}
