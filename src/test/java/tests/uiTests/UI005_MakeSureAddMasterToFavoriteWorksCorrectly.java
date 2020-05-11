package tests.uiTests;

import framework.utils.CustomLogger;
import framework.utils.PropertiesWorker;
import models.SignInUserModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.forms.AuthorisationForm;
import pageObjects.pages.MainPage;
import pageObjects.pages.MastersPage;
import pageObjects.pages.MyProfilePage;

public class UI005_MakeSureAddMasterToFavoriteWorksCorrectly extends BaseUITest {
    @Test
    public void testScenario() throws InterruptedException {
        CustomLogger.makeStepLog(1, "Making authorisation");
        MainPage mainPage = new MainPage();
        mainPage.goToMainMenu().goToSignIn();
        AuthorisationForm authorisationForm = new AuthorisationForm();
        SignInUserModel userModel = new SignInUserModel(
                PropertiesWorker.getTestProperties("exampleUserName"),
                PropertiesWorker.getTestProperties("exampleUserPassword"));
        authorisationForm.makeAuthorisation(userModel);
        CustomLogger.makeStepLog(2, "Clicking masters button");
        mainPage.goToMainMenu().goToMasters();
        CustomLogger.makeStepLog(3, "Going to testing master");
        MastersPage mastersPage = new MastersPage();
        mastersPage.goToMaster(PropertiesWorker.getTestProperties("exampleMasterName"));
        CustomLogger.makeStepLog(4, "Adding to favorites");
        Thread.sleep(3000);
        mastersPage.ClickToAddRemoveButton();
        CustomLogger.makeStepLog(5, "Checking favorites");
        mainPage.goToMainMenu().goToMyProfile();
        MyProfilePage myProfilePage = new MyProfilePage();
        Assert.assertTrue(myProfilePage.isMasterExistAtFavorites(PropertiesWorker.getTestProperties("exampleMasterEmail")), "Studio not exist");
        CustomLogger.makeStepLog(6, "Remove favorites");
        Thread.sleep(3000);
        myProfilePage.removeFirstMaster();
        Thread.sleep(4000);
    }
}
