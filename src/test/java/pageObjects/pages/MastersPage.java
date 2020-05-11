package pageObjects.pages;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class MastersPage {
    public static final String LOCATOR_OF_STUDIO = "//div[contains(@class, 'card-title')]//a[text()='%s']";
    public Label emailLabel = new Label(By.xpath("//div[contains(@class, 'text-center') ]//p"), "Email label");
    public Label nameLabel = new Label(By.xpath("//div[contains(@class, 'text-center') ]//h2"), "Name label");
    public Button addRemoveFavoritesButton = new Button(By.xpath("//button[contains(@class, 'position-absolute') ]"), "Add/remove favorites button");
    public Button addFavoritesButton = new Button(By.xpath("//button[contains(@class, 'position-absolute') and text() = 'Add to favorites' ]"), "Add favorites button");
    public Button removeFavoritesButton = new Button(By.xpath("//button[contains(@class, 'position-absolute') and text() = 'Remove from favorites' ]"), "Remove favorites button");

    public boolean studioIsVisible(String master) {
        return new Label(By.xpath(String.format(LOCATOR_OF_STUDIO, master)), "Masters").isVisible();
    }

    public void goToMaster(String master) {
        Label masterLabel = new Label(By.xpath(String.format(LOCATOR_OF_STUDIO, master)), "Masters studio");
        masterLabel.waitUntilVisible();
        masterLabel.click();
    }

    public String getMasterName() {
        nameLabel.waitUntilVisible();
        return nameLabel.getText();
    }

    public void ClickToAddRemoveButton() {
        addFavoritesButton.waitUntilVisible();
        addRemoveFavoritesButton.moveToElement();
        addRemoveFavoritesButton.click();
        removeFavoritesButton.waitUntilVisible();
    }

    public String getMasterEmail() {
        emailLabel.waitUntilVisible();
        return emailLabel.getText();
    }
}
