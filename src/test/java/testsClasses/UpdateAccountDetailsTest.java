package testsClasses;

import config.SetupTearDown;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AccountDetailsPage;
import pages.LoginPage;
import pages.MyAccountPage;

public class UpdateAccountDetailsTest extends SetupTearDown {

    @Parameters({"firstName", "lastName", "email", "password", "newPswd", "confirmPswd","expectedMessage"})
    @Test
    public void updateAccountPassword(String firstName, String lastName, String email, String password, String newPswd, String confirmPswd,String expectedMessage) {
        // Open Login Page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();

        // Execute Log In
        loginPage.logIn(email, password);

        // Click on Account Details link
        AccountDetailsPage accountDetailsPage = loginPage.clickAccountDetailsLink();

        // Check if all input fields are visible
        accountDetailsPage.verifyAllFieldsAreDisplayed();

        // Update user account info
        MyAccountPage accountPage = accountDetailsPage.updateUserInfo(firstName, lastName, email, password, newPswd, confirmPswd);

        // Verify success message is shown
       accountPage.verifyUpdateSuccessMessage(expectedMessage);
    }

    @Parameters({"firstName", "lastName", "email", "loginPassword", "password", "newPswd", "confirmPswd", "expectedMessage"})
    @Test
    public void failedUpdateAccountPassword(String firstName, String lastName, String email, String loginPassword, String password, String newPswd, String confirmPswd, String expectedMessage) {
        // Open Login Page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();

        // Execute Log In
        loginPage.logIn(email, loginPassword);

        // Click on Account Details link
        AccountDetailsPage accountDetailsPage = loginPage.clickAccountDetailsLink();

        // Check if all input fields are visible
        accountDetailsPage.verifyAllFieldsAreDisplayed();

        // Update user account info
        MyAccountPage accountPage = accountDetailsPage.updateUserInfo(firstName, lastName, email, password, newPswd, confirmPswd);

        // Verify error message is shown
        accountPage.verifyUpdateErrorMessage(expectedMessage);
    }

}
