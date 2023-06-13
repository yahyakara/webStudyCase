package studyCase.configurations;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class TestContext {
    public WebDriver driver;
    public ScenarioContext scenarioContext;
    private static final HashMap<Thread, Scenario> map = new HashMap<>();


    public TestContext(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    public static void putScenario(Scenario scenario) {
        map.put(Thread.currentThread(), scenario);
    }

    public static Scenario getScenario() {
        return map.get(Thread.currentThread());
    }

}
