package pageObjects.menus;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class MainMenu {

    private Button signInButton = new Button(By.xpath("//a[contains(@data-rb-event-key, 'signIn')]"), "Sign in button");
    private Label siteLogoLabel = new Label(By.xpath("//a[contains(@class, 'navbar-brand') and text()='TATTOO4U']"), "Site logo label");
    private Button galleryButton = new Button(By.xpath("//a[contains(@data-rb-event-key, 'gallery') ]"), "Gallery button");
    private Button studiosButton = new Button(By.xpath("//a[contains(@data-rb-event-key, 'studios') ]"), "Studio button");
    private Button mastersButton = new Button(By.xpath("//a[contains(@data-rb-event-key, 'masters') ]"), "Masters button");
    private Button signInUserButton = new Button(By.xpath("//nav[contains(@class, 'navbar')]//a[contains(@class, 'dropdown-toggle nav-link')]"), "Sign in user button");
    public Button profileButton = new Button(By.xpath("//div[contains(@class, 'dropdown-menu') ]//a[text()='Your Profile']"), "Profile button");

    public MainMenu(){

    }

    public void goToSignIn(){
        signInButton.click();
    }

    public boolean isMenuExist(){
        siteLogoLabel.waitUntilVisible();
        return siteLogoLabel.isVisible();
    }

    public void goToMasters() {
        mastersButton.waitUntilVisible();
        mastersButton.click();
    }

    public String getSignInUserName() {
        signInUserButton.waitUntilVisible();
        return signInUserButton.getText();
    }

    public void goToMyProfile() {
        signInUserButton.waitUntilVisible();
        signInUserButton.click();
        profileButton.waitUntilVisible();
        profileButton.click();
    }
}
