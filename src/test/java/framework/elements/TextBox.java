package framework.elements;

import framework.utils.CustomLogger;
import org.openqa.selenium.By;

public class TextBox extends BaseElement {
    public TextBox(By nameOfSelector, String nameOfElement) {
        super(nameOfSelector, nameOfElement);
    }

    public void inputData(String data) {
        CustomLogger.makeLog("Input data in " + name);
        getElement().sendKeys(data);
    }

    public void sendText(String text) {
        CustomLogger.makeLog("Sending text in : " + name);
        getElement().sendKeys(text);
    }

    public String getText() {
        CustomLogger.makeLog("Getting text of element " + name);
        return getElement().getText();
    }

}
