package pageObjects.pages;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class MyProfilePage {
    public static final String LOCATOR_OF_STUDIO = "//div[contains(@class, 'd-flex flex-wrap') ]//p[text() = '%s']";
    public Button firstRemoveMasterButton = new Button(By.xpath("//div[contains(@class, 'd-flex flex-wrap') ]//button[text() = 'Remove']"), "First remove user button");

    public boolean isMasterExistAtFavorites(String masterEmail) {
        return new Label(By.xpath(String.format(LOCATOR_OF_STUDIO, masterEmail)), "Master").isVisible();
    }

    public void removeFirstMaster(){
        firstRemoveMasterButton.waitUntilVisible();
        firstRemoveMasterButton.moveToElement();
        firstRemoveMasterButton.click();
        firstRemoveMasterButton.click();
    }
}
