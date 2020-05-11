package tests;

import framework.utils.CustomLogger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.pages.MainPage;


public class UI001_MakeSureThatUrlOfSiteIsRight extends BaseTest{
    @Test
    public void testScenario() {
        CustomLogger.makeStepLog(1, "Cheking that address is right");
        MainPage mainPage = new MainPage();
        Assert.assertTrue(  mainPage.goToMainMenu().isMenuExist(), "Right page wasn't opened");
    }
}
