package framework.elements;

import framework.utils.CustomLogger;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DropDownList extends BaseElement {
    public DropDownList(By nameOfSelector, String nameOfElement) {
        super(nameOfSelector, nameOfElement);
    }

    public void selectFromList(String selected) {
        CustomLogger.makeLog("Chose from  " + name);
        Select elem = new Select(getElement());
        elem.selectByVisibleText(selected);
    }
}
