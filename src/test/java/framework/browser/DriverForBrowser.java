package framework.browser;

import framework.utils.CustomLogger;
import framework.utils.PropertiesWorker;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class DriverForBrowser {
    private static DriverForBrowser instanceOfSingletonBrowserClass = null;
    private static WebDriver driver;

    private DriverForBrowser() {

        driver = BrowserFactory.setupDriver(PropertiesWorker.getConfigProperties("framework"));

    }

    public static DriverForBrowser getInstanceOfSingletonBrowserClass() {
        if (instanceOfSingletonBrowserClass == null) {
            instanceOfSingletonBrowserClass = new DriverForBrowser();
        }
        return instanceOfSingletonBrowserClass;
    }


    public static WebDriver getDriver() {
        return driver;
    }

    public void implicitly() {
        int time = Integer.parseInt(PropertiesWorker.getConfigProperties("implicitlyWaiting"));
        DriverForBrowser.getDriver().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public void maximize() {
        CustomLogger.makeLog("Window maximize");
        DriverForBrowser.getDriver().manage().window().maximize();
    }

    public void goToAddress(String address) {
        CustomLogger.makeLog("Going to site: " + address);
        DriverForBrowser.getDriver().get(address);
    }

    public void quit() {
        CustomLogger.makeLog("Closing browser");
        driver.quit();
        instanceOfSingletonBrowserClass = null;
    }

}
