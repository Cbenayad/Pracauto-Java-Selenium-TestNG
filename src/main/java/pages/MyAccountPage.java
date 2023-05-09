package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MyAccountPage extends BaseTools {
    private String pageUrl = "https://practice.automationtesting.in/my-account/";
    private By dashboardLink = By.xpath("//a[text()='Dashboard']");
    private By ordersLink = By.xpath("//a[text()='Orders']");
    private By downloadsLink = By.xpath("//a[text()='Downloads']");
    private By addressesLink = By.xpath("//a[text()='Addresses']");
    private By accountDetailsLink = By.xpath("//a[text()='Account Details']");
    private By logoutLink = By.xpath("//a[text()='Logout']");
    private By basketLink = By.xpath("//li[@id='wpmenucartli']/child::a");
    private By loginSuccessMessage = By.xpath("//div[@class='woocommerce-MyAccount-content']/child::p[contains(text(),'Hello')]");
    private By successMessage = By.xpath("//div[@class='woocommerce-message']");
    private By updateErrorMessage = By.xpath("//ul[@class='woocommerce-error']/child::li");
    private By shopLink = By.xpath("//a[text()='Shop']");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * verify page URL
     */

    public void verifyPageUrl() {
        Assert.assertEquals(getCurrentUrl(), pageUrl);
    }

    /**
     * Verify all sidebar links are visible on My Account page
     */
    public void verifySidebarLinksVisibility() {
        Assert.assertTrue(isElementVisible(dashboardLink), "Dashboard link is not visible.");
        Assert.assertTrue(isElementVisible(ordersLink), "Orders link is not visible.");
        Assert.assertTrue(isElementVisible(downloadsLink), "Downloads link is not visible.");
        Assert.assertTrue(isElementVisible(addressesLink), "Addresses link is not visible.");
        Assert.assertTrue(isElementVisible(accountDetailsLink), "Account Details link is not visible.");
        Assert.assertTrue(isElementVisible(logoutLink), "Logout link is not visible.");
    }

    /**
     * Get username from email
     */
    public String getUsername(String email) {
        String[] completeEmail = email.split("@");
        String username = completeEmail[0];
        return username;
    }

    /**
     * Verify success message
     */
    public void getLoginSuccessMessage(String email) {
        String expectedSuccessMessage = "Hello " + getUsername(email) + " (not " + getUsername(email) + "? Sign out)";
        String actualSuccessMessage = getElementText(loginSuccessMessage);
        Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage));
    }

    /**
     * Redirect to Welcome Page by clicking on Logout link
     */
    public MyAccountPage logOut() {
        log.info("Executing LogOut");
        checkElementVisibility(logoutLink);
        clickOnElement(logoutLink, "logout link");
        return new MyAccountPage(driver);
    }

    /**
     * Verify update success message
     */
    public void verifyUpdateSuccessMessage(String expectedMessage) {
        String actualMessage = getElementText(successMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Verify update error message
     */
    public void verifyUpdateErrorMessage(String expectedMessage) {
        String actualMessage = getElementText(updateErrorMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Open Shop Page by clicking on Shop link
     */
    public ShopPage clickShopLink() {
        log.info("Clicking Shop link on My Account Page");
        clickOnElement(shopLink, "Shop link");
        return new ShopPage(driver);
    }

    /**
     * Open Shop Page by clicking on Shop link
     */
    public OrdersHistoryPage clickOrdersLink() {
        log.info("Clicking Orders link on My Account Page");
        clickOnElement(ordersLink, "Orders link");
        return new OrdersHistoryPage(driver);
    }

    /**
     * Open Addresses Page by clicking on Addresses link
     */
    public AddressesPage clickAddressesLink() {
        log.info("Clicking Addresses link on My Account Page");
        clickOnElement(addressesLink, "Addresses link");
        return new AddressesPage(driver);
    }

    /**
     * Open Basket Page by clicking on Basket link
     */
    public BasketPage clickBasketLink() {
        log.info("Clicking Basket link on Welcome Page");
        clickOnElement(basketLink, "Basket link");
        return new BasketPage(driver);
    }

    /**
     * Verify success message after editing address
     */
    public void verifyAddressUpdateSuccessMessage(String expectedMessage) {
        String actualMessage = getElementText(successMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

}
