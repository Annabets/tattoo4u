package framework.elements;

import framework.browser.DriverForBrowser;
import framework.utils.CustomLogger;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public abstract class BaseElement {
    protected String name;
    protected By selector;

    public BaseElement(By nameOfSelector, String nameOfElement) {
        this.selector = nameOfSelector;
        this.name = nameOfElement;

    }

    public WebElement getElement() {
        WebElement element = DriverForBrowser.getDriver().findElement(selector);
        return element;
    }

    public List<WebElement> findSeveral() {
        return DriverForBrowser.getDriver().findElements(selector);
    }

    public boolean isVisible() {
        Waiter.untilVisible(selector);
        CustomLogger.makeLog("Check, that " + name + " is visible");
        return (getElement().isDisplayed());

    }

    public String getText() {
        CustomLogger.makeLog("Getting text of element " + name);
        return getElement().getText();
    }

    public void moveToElement() {
        CustomLogger.makeLog("Move cursor on: " + name);
        Actions actions = new Actions(DriverForBrowser.getDriver());
        actions.moveToElement(getElement()).build().perform();
    }

    public void waitUntilVisible() {
        Waiter.untilVisible(selector);
    }

    public String getAttribute(String attributeType) {
        Waiter.untilVisible(selector);
        CustomLogger.makeLog("Getting attribute " + attributeType + " of element " + name);
        return getElement().getAttribute(attributeType);
    }

    public void waitAndClick() {
        CustomLogger.makeLog("Click on " + name);
        Waiter.untilClickable(selector);
        getElement().click();
    }

    public void click() {
        CustomLogger.makeLog("Click on " + name);
        getElement().click();
    }

}
