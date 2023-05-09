package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AddressesPage extends BaseTools {
    private By title1 = By.xpath("//h3[contains(text(),'Billing Address')]");
    private By title2 = By.xpath("//h3[contains(text(),'Shipping Address')]");
    private By billingEditBtn = By.xpath("//h3[contains(text(),'Billing Address')]/following-sibling::a[@class='edit']");
    private By shippingEditBtn = By.xpath("//h3[contains(text(),'Shipping Address')]/following-sibling::a[@class='edit']");
    private By billingAddressSection = By.xpath("//h3[contains(text(),'Billing Address')]/parent::header/following-sibling::address");
    private By shippingAddressSection = By.xpath("//h3[contains(text(),'Shipping Address')]/parent::header/following-sibling::address");

    public AddressesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyTitlesAreDisplayed() {
        Assert.assertTrue(isElementVisible(title1));
        Assert.assertTrue(isElementVisible(title2));
    }

    public BillingAddressPage clickOnEditBillingAddress() {
        clickOnElement(billingEditBtn, "Edit button for billing address section");
        return new BillingAddressPage(driver);
    }

    public ShippingAddressPage clickOnEditShippingAddress() {
        clickOnElement(shippingEditBtn, "Edit button for shipping address section");
        return new ShippingAddressPage(driver);
    }

    public void compareBillingAddressData(String firstName,String lastName,String address,String postCode,String city,String country){
        Assert.assertEquals(firstName, BillingAddressPage.firstNameValue);
        Assert.assertEquals(lastName, BillingAddressPage.lastNameValue);
        Assert.assertEquals(address, BillingAddressPage.addressValue);
        Assert.assertEquals(postCode.toLowerCase(), BillingAddressPage.postCodeValue.toLowerCase());
        Assert.assertEquals(city, BillingAddressPage.cityValue);
        Assert.assertEquals(country, BillingAddressPage.countryValue);
    }
    public void verifyBillingAddressData() {
        String addressData = getElementText(billingAddressSection);
        String[] dataArray = addressData.split("\n");
        String firstName = dataArray[0].split(" ")[0];
        String lastName = dataArray[0].split(" ")[1];
        String address = dataArray[1];
        String postCode = dataArray[2].split(" ")[0];
        String city = dataArray[2].split(" ")[1];
        String country = dataArray[3];

        compareBillingAddressData(firstName,lastName,address,postCode,city,country);
    }

    public void compareShippingAddressData(String firstName,String lastName,String address,String postCode,String city,String country){
        Assert.assertEquals(firstName, ShippingAddressPage.firstNameValue);
        Assert.assertEquals(lastName, ShippingAddressPage.lastNameValue);
        Assert.assertEquals(address, ShippingAddressPage.addressValue);
        Assert.assertEquals(postCode.toLowerCase(), ShippingAddressPage.postCodeValue.toLowerCase());
        Assert.assertEquals(city, ShippingAddressPage.cityValue);
        Assert.assertEquals(country, ShippingAddressPage.countryValue);
    }

    public void verifyShippingAddressData() {
        String addressData = getElementText(shippingAddressSection);
        String[] dataArray = addressData.split("\n");
        String firstName = dataArray[0].split(" ")[0];
        String lastName = dataArray[0].split(" ")[1];
        String address = dataArray[1];
        String postCode = dataArray[2].split(" ")[0];
        String city = dataArray[2].split(" ")[1];
        String country = dataArray[3];

        compareShippingAddressData(firstName,lastName,address,postCode,city,country);
    }


}
