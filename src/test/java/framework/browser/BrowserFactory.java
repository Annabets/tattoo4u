package framework.browser;

import framework.utils.PropertiesWorker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;

public class BrowserFactory {
    public static WebDriver setupDriver(String browser) {
        switch (browser) {
            case "Chrome":
                return new ChromeDriver(chromeDriverSetup());
            case "Firefox":
                return new FirefoxDriver(firefoxSetup());
            default:
                throw new IllegalStateException("Please, Install driver for Chrome or Firefox Browser!");
        }
    }

    public static ChromeOptions chromeDriverSetup() {
        String language = PropertiesWorker.getConfigProperties("language");
        WebDriverManager.chromedriver().setup();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromePrefs.put("intl.accept_languages", language);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        return chromeOptions;
    }

    public static FirefoxOptions firefoxSetup() {
        String language = PropertiesWorker.getConfigProperties("language");
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("intl.accept_languages", language);
        return firefoxOptions;
    }
}
