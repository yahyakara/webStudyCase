package studyCase.configurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    public static BrowserType getBrowserTypeFromSystemProperty() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        BrowserType browserType;
        switch (browser) {
            case "chrome":
                browserType = BrowserType.CHROME;
                break;
            case "chrome_remote":
                browserType = BrowserType.CHROME_REMOTE;
                break;
            case "firefox":
                browserType = BrowserType.FIREFOX;
                break;
            case "firefox_remote":
                browserType = BrowserType.FIREFOX_REMOTE;
                break;
            case "edge_remote":
                browserType = BrowserType.EDGE_REMOTE;
                break;
            default:
                throw new IllegalStateException("INVALID BROWSER: " + browser);
        }
        return browserType;
    }

    public enum BrowserType {
        CHROME,
        CHROME_REMOTE,
        FIREFOX,
        FIREFOX_REMOTE,
        EDGE_REMOTE,
    }
    public static WebDriver initializeDriver(BrowserType browserType) throws MalformedURLException {
        URL remoteUrl = ConfigLoader.getInstance().getGridUrl();

        WebDriver driver;
        switch (browserType) {
            case CHROME_REMOTE:
                driver = new RemoteWebDriver(remoteUrl, BrowserOptions.getChromeOptions());
                break;
            case CHROME:
                driver = new ChromeDriver(BrowserOptions.getChromeOptions());
                break;
            case FIREFOX_REMOTE:
                driver = new RemoteWebDriver(remoteUrl, BrowserOptions.getFireFoxOptions());
                break;
            case FIREFOX:
                driver = new FirefoxDriver(BrowserOptions.getFireFoxOptions());
                break;
            case EDGE_REMOTE:
                driver = new RemoteWebDriver(remoteUrl, BrowserOptions.getEdgeOptions());
                break;
            default:
                throw new IllegalStateException("INVALID BROWSER: " + browserType);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

}

