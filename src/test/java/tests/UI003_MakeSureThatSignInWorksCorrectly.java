package tests;

import framework.utils.CustomLogger;
import framework.utils.PropertiesWorker;
import models.SignInUserModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.forms.AuthorisationForm;
import pageObjects.pages.MainPage;

public class UI003_MakeSureThatSignInWorksCorrectly extends BaseTest{
    @Test
    public void testScenario() {
        CustomLogger.makeStepLog(1, "Clicking to sign in button");
        MainPage mainPage = new MainPage();
        mainPage.goToMainMenu().goToSignIn();
        CustomLogger.makeStepLog(2, "Making authorisation");
        AuthorisationForm authorisationForm = new AuthorisationForm();
        SignInUserModel userModel = new SignInUserModel(PropertiesWorker.getTestProperties("exampleUserName"), PropertiesWorker.getTestProperties("exampleUserPassword"));
        authorisationForm.makeAuthorisation(userModel);
        CustomLogger.makeStepLog(3, "Checking authorisation was successful");
        Assert.assertEquals(userModel.getName(),  mainPage.goToMainMenu().getSignInUserName(), "Right user didn't sign in!");
    }
}
