package studyCase.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import studyCase.configurations.TestContext;
import studyCase.data.BoutiqueInfo;
import studyCase.pages.HeaderComponent;
import studyCase.pages.HomePage;
import studyCase.pages.LoginPage;
import studyCase.pages.PageFactory;

import java.util.List;
import java.util.Map;

public class HomePageSteps {
    private final TestContext context;
    private LoginPage loginPage;
    private HeaderComponent headerComponent;
    private HomePage homePage;
    List<BoutiqueInfo> boutiqueInfoList;
    Map<String, String> userInfo;

    public HomePageSteps(TestContext context) {
        this.context = context;
        loginPage = PageFactory.getLoginPage(context.driver);
        homePage = PageFactory.getHomePage(context.driver);
    }


    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        homePage.navigateHome();
    }

    @And("I do not want to see the HVTB pop-up")
    public void iDoNotWantToSeeTheHVTBPopUp() {
        homePage.injectHvTbCookie();
    }

    @And("I do not want to see the Optanon alert box closed")
    public void iDoNotWantToSeeTheOptanonAlertBoxClosed() {
        homePage.injectOptananAlertCookie();
    }

    @When("I collect all boutique information")
    public void iCollectAllBoutiqueInformation() {
        boutiqueInfoList = homePage.collectBoutiquesInfo();
    }



    @Then("I measure and report the status of boutique links and their images, and I want the results in an Excel file named {string}")
    public void iMeasureAndReportTheStatusOfBoutiqueLinksAndTheirImagesAndIWantTheResultsInAnExcelFileNamed(String fileName) {
        homePage.exportBoutiqueInfoToExcel(boutiqueInfoList, fileName);
    }

    @Given("I am on electronic boutique page")
    public void iAmOnElectronicBoutiquePage() {
        homePage.navigateElectronicBoutiquePage();
    }
}
