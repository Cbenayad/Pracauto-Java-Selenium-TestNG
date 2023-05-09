package testsClasses;

import config.SetupTearDown;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.WelcomePage;

public class LogoutTest extends SetupTearDown {

    @Parameters({"email", "password"})
    @Test
    public void logoutTest(String email, String password) {
        log.info("Starting logout test");

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

        // verify My Account page url
        myAccountPage.verifyPageUrl();
    }
}
