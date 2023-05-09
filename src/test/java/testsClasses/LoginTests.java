package testsClasses;

import config.SetupTearDown;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.WelcomePage;

public class LoginTests extends SetupTearDown {

    @Parameters({"email", "password"})
    @Test
    public void positiveLoginTest(String email, String password) {
        log.info("Starting logIn test");

        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(email, password);

        // Verify new page url is as expected
        myAccountPage.verifyPageUrl();

        // All sidebar links are visible
        myAccountPage.verifySidebarLinksVisibility();

        // Successful login message
        myAccountPage.getLoginSuccessMessage(email);

        // click on logout link
        myAccountPage.logOut();
    }

    @Parameters({"email", "password"})
    @Test
    public void selectRememberMeCheckbox(String email, String password) {
        log.info("Starting logIn test with selecting Remember me checkbox");

        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logInRememberMe(email, password);

        // verify new page url is as expected
        myAccountPage.verifyPageUrl();
        // click on logout link
        myAccountPage.logOut();

        // Verify input fields are prefilled
        loginPage.verifyPrefilledInputFields(email, password);

    }

    @Parameters({"login", "password", "expectedMessage"})
    @Test
    public void negativeLoginTest(String login, String password, String expectedErrorMessage) {
        log.info("Starting negative logIn test");

        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute negative login
        loginPage.negativeLogIn(login, password);

        // error login message
        loginPage.verifyErrorMessage(expectedErrorMessage);

    }


}
