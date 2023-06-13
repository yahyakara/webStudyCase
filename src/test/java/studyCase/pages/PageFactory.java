package studyCase.pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {

    private static LoginPage loginPage;
    private static HeaderComponent headerComponent;
    public static LoginPage getLoginPage(WebDriver driver){
        return loginPage == null ? new LoginPage(driver) : loginPage;
    }
    public static HeaderComponent getHeaderComponent(WebDriver driver){
        return headerComponent == null ? new HeaderComponent(driver) : headerComponent;
    }



}
