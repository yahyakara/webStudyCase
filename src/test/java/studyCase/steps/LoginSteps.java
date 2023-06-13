package studyCase.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import studyCase.configurations.TestContext;
import studyCase.constants.Context;
import studyCase.pages.HeaderComponent;
import studyCase.pages.LoginPage;
import studyCase.pages.PageFactory;
import java.util.Map;
import static studyCase.data.DataHelper.getUserDataByIndex;

public class LoginSteps {
    private final TestContext context;
    private LoginPage loginPage;
    private HeaderComponent headerComponent;
    Map<String, String> userInfo;

    public LoginSteps(TestContext context) {
        this.context = context;
        loginPage = PageFactory.getLoginPage(context.driver);
        headerComponent = PageFactory.getHeaderComponent(context.driver);
    }

    @Given("I go to login page")
    public void iGoToLoginPage() {
        loginPage.navigateLoginPage();
    }

    @Given("I navigate to the login page by clicking it from the header menu")
    public void iNavigateToLoginPage() {
        headerComponent.navigateToLoginPage();
    }

    @Given("I log in to the app with valid credentials - data index {int}")
    public void iLogInToTheAppWithValidCredentials(int index) {
        userInfo = getUserDataByIndex(index);
        assert userInfo != null;
        String userName = userInfo.get("username");
        String password = userInfo.get("password");

        loginPage.fillTheLoginForm(userName, password);
        loginPage.submitLoginForm();
        context.scenarioContext.setContext(Context.USER_CREDENTIAL, userInfo);
    }


    @Then("I should be on the login page")
    public void iShouldBeOnTheLoginPage() {
        loginPage.validateLoginPageUrl();
    }

    @And("I should see my full name in the navigation bar")
    public void iShouldSeeMyFullNameInTheNavigationBar() {
        String fullName = userInfo.get("fullName");
        headerComponent.validateUserFullName(fullName);
    }

    @When("I fill the login form {string} and {string}")
    public void iFillTheLoginForm(String userName, String password) {
        loginPage.fillTheLoginForm(userName, password);
    }

    @And("I click the Sign in button")
    public void iClickTheSignInButton() {
        loginPage.submitLoginForm();
    }

    @Then("I should see the login failed error {string}")
    public void iShouldSeeTheLoginFailedError(String expectedErrorMessage) {
        loginPage.validateErrorMessage(expectedErrorMessage);
    }

    @And("I show the hided password")
    public void iClicksOnTheEyeOpenIcon() {
        loginPage.clickEyeCloseBtn();
    }

    @And("I hide the password")
    public void iClicksOnTheEyeCloseIcon() {
        loginPage.clickEyeOpenBtn();
    }

    @Then("I should be able to view the password")
    public void iShouldBeAbleToViewThePassword() {
        loginPage.validatePasswordInputType("text");
    }

    @Then("I shouldn't be able to view the password")
    public void iShouldNotBeAbleToViewThePassword() {
        loginPage.validatePasswordInputType("password");
    }
}
