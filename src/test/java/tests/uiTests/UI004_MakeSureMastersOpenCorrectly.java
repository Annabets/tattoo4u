package tests.uiTests;

import framework.utils.CustomLogger;
import framework.utils.PropertiesWorker;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.pages.MainPage;
import pageObjects.pages.MastersPage;

public class UI004_MakeSureMastersOpenCorrectly extends BaseUITest{
    @Test
    public void testScenario() {
        CustomLogger.makeStepLog(1, "Clicking masters button");
        MainPage mainPage = new MainPage();
        mainPage.goToMainMenu().goToMasters();
        CustomLogger.makeStepLog(2, "Going to testing master");
        MastersPage mastersPage = new MastersPage();
        mastersPage.goToMaster(PropertiesWorker.getTestProperties("exampleMasterName"));
        CustomLogger.makeStepLog(3, "Checking user");
        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertEquals(mastersPage.getMasterName(),  PropertiesWorker.getTestProperties("exampleMasterName"), "Not right email");
        softAssertion.assertEquals(mastersPage.getMasterEmail(),  PropertiesWorker.getTestProperties("exampleMasterEmail"), "Not right email");
    }
}
