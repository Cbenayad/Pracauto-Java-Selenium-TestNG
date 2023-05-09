package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class BillingAddressPage extends BaseTools {
    private By pageHeader = By.xpath("//h3[contains(text(),'Billing Address')]");
    private By firstNameField = By.xpath("//input[@id='billing_first_name']");
    private By lastNameField = By.xpath("//input[@id='billing_last_name']");
    private By emailAddressField = By.xpath("//input[@id='billing_email']");
    private By phoneField = By.xpath("//input[@id='billing_phone']");
    private By countryDiv = By.xpath("//div[@id='s2id_billing_country']");
    private By addressField = By.xpath("//input[@id='billing_address_1']");
    private By cityField = By.xpath("//input[@id='billing_city']");
    private By postCodeField = By.xpath("//input[@id='billing_postcode']");
    private By saveButton = By.xpath("//input[@name='save_address']");
    private By selectedCountry = By.xpath("//span[@id='select2-chosen-1']");
    private By errorMessages = By.xpath("//ul[@class='woocommerce-error']/li");
    public static String firstNameValue;
    public static String lastNameValue;
    public static String addressValue;
    public static String postCodeValue;
    public static String cityValue;
    public static String countryValue;

    public BillingAddressPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyPageHeader() {
        Assert.assertTrue(isElementVisible(pageHeader));
    }

    public void selectCountry(String country) {
        Actions actions = new Actions(driver);
        actions.moveToElement(find(countryDiv)).perform();
        clickOnElement(countryDiv, "country division");
        WebElement countryOption = driver.findElement(By.xpath("//ul[@id='select2-results-1']/li[@role='presentation']/div[contains(text(),'" + country + "')]"));
        actions.moveToElement(countryOption).perform();
        countryOption.click();
    }

    public void FillBillingAddressForm(String firstName, String lastName, String email, String phone, String country, String address, String postCode, String city) {
        typeText(firstNameField, firstName);
        firstNameValue = getFirstName();
        typeText(lastNameField, lastName);
        lastNameValue = getLastName();
        typeText(emailAddressField, email);
        typeText(phoneField, phone);
        selectCountry(country);
        countryValue = getCountry();
        typeText(addressField, address);
        addressValue = getAddress();
        typeText(postCodeField, postCode);
        postCodeValue = getPostCode();
        typeText(cityField, city);
        cityValue = getCity();
    }

    public MyAccountPage clickOnSaveButton() {
        clickOnElement(saveButton, "SAVE ADDRESS button");
        return new MyAccountPage(driver);
    }

    public String getFirstName() {
        return getInputValue(firstNameField);
    }

    public String getLastName() {
        return getInputValue(lastNameField);
    }

    public String getAddress() {
        return getInputValue(addressField);
    }

    public String getPostCode() {
        return getInputValue(postCodeField);
    }

    public String getCity() {
        return getInputValue(cityField);
    }

    public String getCountry() {
        return getElementText(selectedCountry);
    }

    /**
     * Verify invalid fields error messages are as expected
     */
    public void verifyErrorMessages(String expectedMessage) {
        List<WebElement> errors = driver.findElements(errorMessages);
        StringBuilder actualErrorMessage = new StringBuilder();
        for (WebElement error : errors) {
            actualErrorMessage.append(error.getText());
        }
         Assert.assertTrue(actualErrorMessage.toString().contains(expectedMessage));
    }
}
