package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BaseTools {
    private String pageUrl = "https://practice.automationtesting.in/my-account/";
    private By loginField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.name("login");
    private By rememberMeButton = By.id("rememberme");
    private By errorMessage = By.xpath("//ul[@class='woocommerce-error']/child::li");
    private By emailRegistrationField = By.id("reg_email");
    private By passwordRegistrationField = By.id("reg_password");
    private By registerButton = By.name("register");
    private By passwordHint = By.xpath("//small[@class='woocommerce-password-hint']");
    private By accountDetailsLink = By.xpath("//a[text()='Account Details']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Open WelcomePage with it's url
     */
    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    /**
     * Execute login
     */
    public MyAccountPage logIn(String login, String password) {
        log.info("Executing LogIn with username or email address [" + login + "] and password [" + password + "]");
        typeText(loginField, login);
        typeText(passwordField, password);
        clickOnElement(loginButton, "login button");
        return new MyAccountPage(driver);
    }

    /**
     * Execute login with selecting Remember me button
     */
    public MyAccountPage logInRememberMe(String login, String password) {
        log.info("Executing LogIn with username or email address [" + login + "] and password [" + password + "] and checking Remember me checkbox");
        typeText(loginField, login);
        typeText(passwordField, password);
        clickOnElement(rememberMeButton, "Remember me checkbox");
        clickOnElement(loginButton, "login button");
        return new MyAccountPage(driver);
    }

    /**
     * Verify email & password input fields are prefilled
     */
    public void verifyPrefilledInputFields(String email, String password) {
        String actualLoginInputText = getInputValue(loginField);
        Assert.assertEquals(actualLoginInputText, email);
        String actualPasswordInputText = getInputValue(passwordField);
        Assert.assertEquals(actualPasswordInputText, password);
    }

    /**
     * Execute negative login
     */
    public void negativeLogIn(String login, String password) {
        log.info("Executing LogIn with username or email address [" + login + "] and password [" + password + "]");
        typeText(loginField, login);
        typeText(passwordField, password);
        clickOnElement(loginButton, "login button");
    }

    /**
     * verify error message
     */
    public void verifyErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = getElementText(errorMessage);
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }

    /**
     * Create a new account
     */
    public MyAccountPage register(String email, String password) {
        log.info("Creating a new account with email address [" + email + "] and password [" + password + "]");
        typeText(emailRegistrationField, email);
        typeText(passwordRegistrationField, password);
        clickOnElement(registerButton, "register button");
        return new MyAccountPage(driver);
    }

    /**
     * Execute negative registration
     */
    public void negativeRegistration(String email, String password) {
        log.info("Executing registration with email address [" + email + "] and password [" + password + "]");
        typeText(emailRegistrationField, email);
        typeText(passwordRegistrationField, password);
        clickOnElement(registerButton, "register button");
    }

    /**
     * Verification if register button is displayed on the page
     */
    public void verifyRegisterBtnDisplayed() {
        Assert.assertTrue(isElementVisible(registerButton), "Register button is not displayed.");
    }

    /**
     * Execute negative registration with invalid password
     */
    public void invalidPassword(String email, String password) {
        log.info("Executing registration with email address [" + email + "] and password [" + password + "]");
        typeText(emailRegistrationField, email);
        typeText(passwordRegistrationField, password);
    }


    /**
     * verify hint message
     */
    public void verifyHintMessage(String expectedHintMessage) {
        String actualErrorMessage = getElementText(passwordHint);
        Assert.assertTrue(actualErrorMessage.contains(expectedHintMessage));
    }

    /**
     * Open Account Details by clicking on Account Details link
     */
    public AccountDetailsPage clickAccountDetailsLink() {
        log.info("Clicking Form Authentication link on Welcome Page");
        clickOnElement(accountDetailsLink, "Account Details link");
        return new AccountDetailsPage(driver);
    }

}
