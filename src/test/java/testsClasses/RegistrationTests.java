package testsClasses;

import config.SetupTearDown;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.WelcomePage;

public class RegistrationTests extends SetupTearDown {
    @Parameters({"email", "password"})
    @Test
    public void registerTest(String email, String password) {
        log.info("Starting registration test");

        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.register(email, password);

        // Verifications
        // New page url is expected
        myAccountPage.verifyPageUrl();

        // All sidebar links are visible
        myAccountPage.verifySidebarLinksVisibility();

        // Successful login message
        myAccountPage.getLoginSuccessMessage(email);

        //click on logout link
        myAccountPage.logOut();
    }

    @Parameters({"email", "password", "expectedMessage"})
    @Test
    public void existentEmail(String email, String password, String expectedErrorMessage) {
        log.info("Starting negative registration test");

        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute negative registration
        loginPage.negativeRegistration(email, password);

        // verify error message
        loginPage.verifyErrorMessage(expectedErrorMessage);
    }

    @Parameters({"email", "password"})
    @Test
    public void invalidEmail(String email, String password) {
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute negative registration
        loginPage.negativeRegistration(email, password);

        // verify register button is displayed
        loginPage.verifyRegisterBtnDisplayed();
    }

    @Parameters({"email", "password", "expectedMessage"})
    @Test
    public void invalidPassword(String email, String password, String expectedHintMessage) {
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute negative registration
        loginPage.invalidPassword(email, password);

        //Verifications
        // hint message
        loginPage.verifyHintMessage(expectedHintMessage);
    }

}
