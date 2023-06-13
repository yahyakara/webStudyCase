package studyCase.configurations;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

public class BrowserOptions {
    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.DRIVER, Level.ALL);
        chromeOptions.setCapability("goog:loggingPrefs", logPrefs);
        chromeOptions.addArguments("--remote-allow-origins=*");
        return chromeOptions;
    }

    public static FirefoxOptions getFireFoxOptions() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("devtools.console.stdout.content", true);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions
                .setProfile(firefoxProfile);
        return firefoxOptions;
    }

    public static EdgeOptions getEdgeOptions() {
        return new EdgeOptions();
    }

}
