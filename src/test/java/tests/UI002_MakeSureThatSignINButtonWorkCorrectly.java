package tests;

import framework.utils.CustomLogger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.forms.AuthorisationForm;
import pageObjects.pages.MainPage;

public class UI002_MakeSureThatSignINButtonWorkCorrectly extends BaseTest {
    @Test
    public void testScenario() {
        CustomLogger.makeStepLog(1, "Clicking to sign in button");
        MainPage mainPage = new MainPage();
        mainPage.goToMainMenu().goToSignIn();
        AuthorisationForm authorisationForm = new AuthorisationForm();
        CustomLogger.makeStepLog(2, "Checking authorisation form is exist");
        Assert.assertTrue(authorisationForm.isSignInFormExist(), "Sign in form doesn't exist!");
    }
}
