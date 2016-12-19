package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ResultPage;

import static tests.BaseTest.getWebDriver;

/**
 * Created by lastochkin on 12/16/16.
 */
public class UITests {
    private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
    private MainPage mainPage = PageFactory.initElements(getWebDriver(), MainPage.class);
    private ResultPage resultPage = PageFactory.initElements(getWebDriver(), ResultPage.class);

    @Test
    public void addNewContacts(){
        loginPage.login(getWebDriver());
        mainPage.advancedSearch();
        resultPage.addConnects();
    }

    @Test
    public void test(){
        for(int i = 1; i <=10; i++){
            System.out.println(i);
        }
    }
}
