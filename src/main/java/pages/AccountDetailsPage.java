package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountDetailsPage extends BaseTools {

    private String pageUrl = "https://practice.automationtesting.in/my-account/edit-account/";
    private By firstNameField = By.id("account_first_name");
    private By lastNameField = By.id("account_last_name");
    private By emailField = By.id("account_email");
    private By currentPasswordField = By.id("password_current");
    private By newPasswpordField = By.id("password_1");
    private By confirmNewPasswordField = By.id("password_2");
    private By saveChangesButton = By.name("save_account_details");

    public AccountDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Verify all input fields are displayed
     */
    public void verifyAllFieldsAreDisplayed() {
        Assert.assertTrue(isElementVisible(firstNameField));
        Assert.assertTrue(isElementVisible(lastNameField));
        Assert.assertTrue(isElementVisible(emailField));
        Assert.assertTrue(isElementVisible(currentPasswordField));
        Assert.assertTrue(isElementVisible(newPasswpordField));
        Assert.assertTrue(isElementVisible(confirmNewPasswordField));
        Assert.assertTrue(isElementVisible(saveChangesButton));
    }

    /**
     * Update user account details
     */
    public MyAccountPage updateUserInfo(String firstName, String lastName, String email, String currPassword, String newPassword, String confirmPassword) {
        log.info("Updating the user account details");
        // Fill all the input fields
        typeText(firstNameField, firstName);
        typeText(lastNameField, lastName);
        typeText(emailField, email);
        typeText(currentPasswordField, currPassword);
        typeText(newPasswpordField, newPassword);
        typeText(confirmNewPasswordField, confirmPassword);
        // Click on Save Changes button
        clickOnElement(saveChangesButton, "Save Changes button");
        return new MyAccountPage(driver);
    }

}
