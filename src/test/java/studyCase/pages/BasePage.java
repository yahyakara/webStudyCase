package studyCase.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import studyCase.configurations.ConfigLoader;


import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static studyCase.configurations.TestContext.getScenario;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitAndFindElement(By locator) {
        getScenario().log("Waiting visibility for : " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> waitAndFindElements(By locator) {
        getScenario().log("Waiting visibility for elements located by: " + locator);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        getScenario().log("Waiting visibility for : " + locator);
       return  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean isElementExist(By locator, long millis) {
        getScenario().log("Is element exist: " + locator);
        long endTime = System.currentTimeMillis() + millis;
        while (System.currentTimeMillis() < endTime) {
            if (driver.findElements(locator).size() > 0) {
                return true;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    protected void loadPage(String endPoint, String pageName) {
        getScenario().log(String.format("%s page  is loading", pageName));
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    protected void click(By elementBy) {
        getScenario().log(String.format("Clicking to %s ", elementBy));
        wait.until(ExpectedConditions.elementToBeClickable(elementBy)).click();
    }

    protected String getText(By locator) {
        getScenario().log(String.format("Get text from  %s ", locator));
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return e.getText();
    }

    protected void type(By locator, String text) {
        getScenario().log(String.format("Typing : %s  to %s", text, locator));
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        e.clear();
        e.sendKeys(text);
    }

    protected void waitForLoaderToDisappear() {
        By loaderLocator = By.cssSelector(".q-loader");
        waitUntilElementDisappear(loaderLocator);
    }

    protected void waitUntilElementDisappear(By locator) {
        getScenario().log(String.format("Waiting for %s to appear", locator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        getScenario().log("Element disappeared");
    }

    protected void checkCurrentURLPath(String expectedPath) {
        String currentUrl = driver.getCurrentUrl();
        try {
            URL url = new URL(currentUrl);
            String path = url.getPath();
            Assert.assertTrue(path.contains(expectedPath), String.format("path should contain %s", expectedPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setLocalStorageItem(String key, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(String.format("window.localStorage.setItem('%s', '%s');", key, value));
    }

    protected  void injectCookies(Cookie cookie) {
        driver.manage().addCookie(cookie);
        refreshThePage();
    }


    protected void mouseOver(By locator) {
        WebElement element  = waitAndFindElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void scrollElementIntoView(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void handlePopup(By by) throws InterruptedException {
        waitForElementToBeVisible(by);
        List<WebElement> popup = driver.findElements(by);
        if (!popup.isEmpty()) {
            popup
                    .get(0)
                    .click();
            Thread.sleep(200);
        }
    }

    protected String getBrowserTitle() {
        String title = driver.getTitle();
        getScenario().log(String.format("Browser Tile :  %s", title));
        return title;
    }

    protected int getCurrentElementCount(By locator) {
        return  waitAndFindElements(locator).size();
    }
    protected String getAttributeValue(WebElement element, String attributeName) {
        WebElement e = wait.until(ExpectedConditions.visibilityOf(element));
        return e.getAttribute(attributeName);
    }

    protected void scrollToEndOfPage() {
        Actions actions = new Actions(driver);
        WebElement body = waitAndFindElement(By.tagName("body"));
        actions.moveToElement(body).sendKeys("\uE00F").perform();
    }

    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void switchToIframe(WebElement iframeElement) {
        driver.switchTo().frame(iframeElement);
    }

    protected Alert getAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    protected void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    protected void refreshThePage() {
        driver.navigate().refresh();
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void checkAlertMessage(String expectedAlertMessage) {
        Alert alert = getAlert();
        String actualAlertMessage = alert.getText();
        assertEquals(actualAlertMessage, expectedAlertMessage, String.format("Alert message %s", expectedAlertMessage));
    }

    protected void closeAlert() {
        Alert alert = getAlert();
        alert.dismiss();
    }

    protected String switchToNewWindow(WebDriver driver, String currentWindowHandle) {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                return windowHandle;
            }
        }

        throw new RuntimeException("Can't find new window");
    }

}
