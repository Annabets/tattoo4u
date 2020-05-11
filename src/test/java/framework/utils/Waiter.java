package framework.utils;

import framework.browser.DriverForBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class Waiter {
    public static void untilClickable(By locator) {
        int time = Integer.parseInt(PropertiesWorker.getConfigProperties("waitingUntilClickable"));
        WebDriverWait waiting = new WebDriverWait(DriverForBrowser.getDriver(), time);
        waiting.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void untilInvisible(By locator) {
        int time = Integer.parseInt(PropertiesWorker.getConfigProperties("waitingUntilInvisible"));
        WebDriverWait waiting = new WebDriverWait(DriverForBrowser.getDriver(), time);
        waiting.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void untilVisible(By locator) {
        int time = Integer.parseInt(PropertiesWorker.getConfigProperties("waitingUntilInvisible"));
        new WebDriverWait(DriverForBrowser.getDriver(), time)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void awaitityWait(Boolean event) {
        await().atMost(30, SECONDS).until(() -> event);
    }

}
