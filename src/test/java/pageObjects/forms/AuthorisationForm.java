package pageObjects.forms;

import framework.elements.Button;
import framework.elements.Label;
import framework.elements.TextBox;
import framework.utils.Waiter;
import models.SignInUserModel;
import org.openqa.selenium.By;

public class AuthorisationForm {
    private Label signInLabel = new Label(By.xpath("//h2[@class='form-title'  and text()='Sign In']"), "Sign in label");
    private TextBox userNameTextBox = new TextBox(By.xpath("//input[@placeholder='Username']"), "User name text box");
    private TextBox passwordTextBox = new TextBox(By.xpath("//input[@placeholder='Password']"), "Password text box");
    private Button submitButton = new Button(By.xpath("//button[@type='submit']"), "Submit button");

    public boolean isSignInFormExist() {
        signInLabel.waitUntilVisible();
        return signInLabel.isVisible();
    }

    public void inputUserName(String userName) {
        userNameTextBox.waitUntilVisible();
        userNameTextBox.sendText(userName);
    }

    public void inputPassword(String password) {
        passwordTextBox.sendText(password);
    }

    public void makeAuthorisation(SignInUserModel model){
        inputUserName(model.getName());
        inputPassword(model.getPassword());
        submitButton.click();
    }

}
