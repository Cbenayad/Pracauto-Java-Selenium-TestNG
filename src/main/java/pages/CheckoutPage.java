package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class CheckoutPage extends BaseTools {
    private String pageUrl = "https://practice.automationtesting.in/checkout/";
    private By firstNameField = By.xpath("//input[@id='billing_first_name']");
    private By lastNameField = By.xpath("//input[@id='billing_last_name']");
    private By emailAddressField = By.xpath("//input[@id='billing_email']");
    private By phoneField = By.xpath("//input[@id='billing_phone']");
    private By countryDiv = By.xpath("//div[@id='s2id_billing_country']");
    private By addressField = By.xpath("//input[@id='billing_address_1']");
    private By cityField = By.xpath("//input[@id='billing_city']");
    //private By stateField = By.xpath("//input[@id='billing_state']");
    private By postCodeField = By.xpath("//input[@id='billing_postcode']");
    private By billingSectionTitle = By.xpath("//h3[contains(text(),'Billing Details')]");
    private By orderSectionTitle = By.xpath("//h3[@id='order_review_heading']");
    private By subtotal = By.xpath("//tr[@class='cart-subtotal']/th[contains(text(),'Subtotal')]/following-sibling::td/span[contains(@class,'amount')]");
    private By tax = By.xpath("//tr[contains(@class,'tax-rate')]/th[contains(text(),'Roaming Tax')]/following-sibling::td/span[contains(@class,'amount')]");
    private By total = By.xpath("//tr[@class='order-total']/th[contains(text(),'Total')]/following-sibling::td/strong/span[contains(@class,'amount')]");
    private By paymentOptions = By.xpath("//div[@id='payment']");
    private By directBankTransferPaymentOption = By.xpath("//input[@id='payment_method_bacs']");
    private By checkPaymentOption = By.xpath("//input[@id='payment_method_cheque']");
    private By CashPaymentOption=By.xpath("//input[@id='payment_method_cod']");
    private By placeOrderBtn = By.xpath("//input[@id='place_order']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyPageUrl() {
        Assert.assertEquals(getCurrentUrl(), pageUrl);
    }

    public void verifySectionTitlesVisibility() {
        Assert.assertTrue(isElementVisible(billingSectionTitle));
        Assert.assertTrue(isElementVisible(orderSectionTitle));
        //Assert.assertTrue(isElementVisible(paymentOptions));
    }

    public void selectCountry(String country) {
        Actions actions = new Actions(driver);
        actions.moveToElement(find(countryDiv)).perform();
        clickOnElement(countryDiv, "country division");
        WebElement countryOption = driver.findElement(By.xpath("//ul[@id='select2-results-1']/li[@role='presentation']/div[contains(text(),'" + country + "')]"));
        actions.moveToElement(countryOption).perform();
        countryOption.click();
    }

    public void fillCustomerDetails(String firstName, String lastName, String email, String phone, String country, String address, String postCode, String city) {
        typeText(firstNameField, firstName);
        typeText(lastNameField, lastName);
        typeText(emailAddressField, email);
        typeText(phoneField, phone);
        selectCountry(country);
        typeText(addressField, address);
        typeText(postCodeField, postCode);
        typeText(cityField, city);
    }
    public String getSubtotal() {
        return getElementText(subtotal);
    }

    public String getTax() {
        return getElementText(tax);
    }

    public String getTotal() {
        return getElementText(total);
    }

    public void verifyProductDetails(BasketPage basketPage) {
        //BasketPage basketPage = new BasketPage(driver, log);
        Assert.assertEquals(getSubtotal(), basketPage.subtotal);
        Assert.assertEquals(getTax(), basketPage.tax);
        Assert.assertEquals(getTotal(), basketPage.total);
    }
    public void selectDirectBankTransferPaymentOption(){
        clickOnElement(directBankTransferPaymentOption,"Direct Bank Transfer radio button");
    }
    public void selectCheckPaymentOption(){
        clickOnElement(checkPaymentOption,"Check Payments radio button");
    }

    public void selectCashPaymentOption(){
        clickOnElement(checkPaymentOption,"Cash on Delivery radio button");
    }

    public OrderDetailsPage clickOnPlaceOrderButton() {
        clickOnElement(placeOrderBtn, "PLACE ORDER button");
        return new OrderDetailsPage(driver);
    }

}
