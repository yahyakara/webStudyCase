package studyCase.configurations;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;


public class Hooks {
    private WebDriver driver;
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void before() throws MalformedURLException {
        driver = DriverFactory.initializeDriver(DriverFactory.getBrowserTypeFromSystemProperty());
        context.driver = driver;
    }

    @Before(order = 1)
    public void putScenario(Scenario scenario) {
        TestContext.putScenario(scenario);
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] data = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", "Failed screenshot");
        }
        driver.quit();
    }

}
