package ContextIs;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.WelcomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyStepDefs {

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    WelcomePage welcomePage = new WelcomePage();

    @Given("^User is on homepage$")
    public void user_is_on_homepage() {

    }

    @When("^User navigates to login page$")
    public void user_navigates_to_login_page() {
        homePage.userShouldBeAbleToNavigateToLoginPage();
    }

    @When("^User enters \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enters_and(String username, String password) {
        loginPage.userShouldBeAbleToLoginAndNavigateToWelcomePage(username, password);
    }

    @When("^User clicks on Login$")
    public void user_clicks_on_Login() {
        loginPage.verifyUserIsOnWelcomePage();
    }

    @When("^User clicks on upload$")
    public void user_clicks_on_upload() {
        welcomePage.userShouldBeAbleToUploadFile();
    }

    @When("^User clicks on choose file$")
    public void user_clicks_on_choose_file() {

    }

    @When("^User selects a file$")
    public void user_selects_a_file() {
        welcomePage.userSelectsAfileToUpload();
    }

    @When("^User clicks on upload file$")
    public void user_clicks_on_upload_file() {

    }

    @Then("^User should be directed to welcome page$")
    public void user_should_be_directed_to_welcome_page() {
        welcomePage.verifyFileIsSuccessfullyUploaded();
    }


}
