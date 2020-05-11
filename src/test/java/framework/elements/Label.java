package framework.elements;

import framework.utils.CustomLogger;
import org.openqa.selenium.By;

public class Label extends BaseElement {
    public Label(By nameOfSelector, String nameOfElement) {
        super(nameOfSelector, nameOfElement);
    }

    public void sendText(String text) {
        CustomLogger.makeLog("Sending text in: " + name);
        getElement().sendKeys(text);
    }

}
