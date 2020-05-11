package tests.uiTests;

import framework.utils.CustomLogger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.pages.MainPage;
import tests.uiTests.BaseUITest;


public class UI001_MakeSureThatUrlOfSiteIsRight extends BaseUITest {
    @Test
    public void testScenario() {
        CustomLogger.makeStepLog(1, "Cheking that address is right");
        MainPage mainPage = new MainPage();
        Assert.assertTrue(  mainPage.goToMainMenu().isMenuExist(), "Right page wasn't opened");
    }
}
