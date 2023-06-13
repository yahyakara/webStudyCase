package studyCase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import studyCase.constants.EndPoint;

import static org.testng.Assert.assertEquals;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By emailInputBy = By.cssSelector("#login-email");
    private final By passwordInputBy = By.cssSelector("#login-password-input");
    private final By eyeCloseBtnBy = By.cssSelector(".i-eye-close");
    private final By eyeOpenBtnBy = By.cssSelector(".i-eye-open");
    private final By loginBy = By.cssSelector(".submit");
    private final By messageBy = By.cssSelector(".message");

    public void navigateLoginPage() {
        loadPage(EndPoint.LOGIN_PAGE.url, "loginPage");
    }

    public void fillTheUserName(String userName) {
        type(emailInputBy, userName);
    }

    public void fillThePassword(String password) {
        type(passwordInputBy, password);
    }

    public void fillTheLoginForm(String userName, String password) {
        fillTheUserName(userName);
        fillThePassword(password);
    }

    public void validateLoginPageUrl() {
        checkCurrentURLPath("giris");
    }

    public void openThePasswordEye() {
        click(eyeOpenBtnBy);
    }

    public void closeThePasswordEye() {
        click(eyeCloseBtnBy);
    }

    public void validateErrorMessage(String expectedError) {
        String actualError = getText(messageBy);
        assertEquals(actualError, expectedError, String.format("%s Error message should %s", actualError, expectedError));
    }

    public void clickEyeOpenBtn() {
        click(eyeOpenBtnBy);
    }

    public void clickEyeCloseBtn() {
        click(eyeCloseBtnBy);
    }


    public void submitLoginForm() {
        click(loginBy);
        waitForLoaderToDisappear();
    }

    public void validatePasswordInputType(String expectedType) {
        WebElement passwordInput = waitAndFindElement(passwordInputBy);
        String actualType = getAttributeValue(passwordInput, "type");
        assertEquals(actualType, expectedType, String.format("Password input type should be %s ", expectedType));
    }


}
