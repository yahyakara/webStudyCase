package studyCase.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HeaderComponent extends BasePage {
    public HeaderComponent(WebDriver driver) {
        super(driver);
    }
    private final By loginBtnBy = By.cssSelector(".login-button");
    private final By loggedInContainerBy = By.cssSelector(".user-login-container");
    private final By searchInputBy = By.cssSelector(".new-user-loggedin-container > p");
    private final By searchBtnBy = By.cssSelector("i[data-testid=\"search-icon\"]");
    private final By userNameTextBy = By.cssSelector(".new-user-loggedin-container > p");
    private final By basketBtnBy = By.cssSelector(".basket-preview");
    private final By favoriteBtnBy = By.cssSelector(".account-favorites");

    public void openUserLoginContainer() {
        mouseOver(loggedInContainerBy);
    }

    public void navigateToLoginPage() {
        openUserLoginContainer();
        click(loginBtnBy);
    }

    public void navigateToBasketPage() {
        click(basketBtnBy);
    }

    public void searchSomething(String keyword) {
        click(searchInputBy);
        type(searchInputBy, keyword);
        click(searchBtnBy);
    }

    public void validateUserFullName(String expectedName) {
        openUserLoginContainer();
        String actualName = getText(userNameTextBy);
        Assert.assertEquals(actualName, expectedName, String.format("User full name should be %s", expectedName));
    }

    public void navigateToFavorites() {
        click(favoriteBtnBy);
    }


}
