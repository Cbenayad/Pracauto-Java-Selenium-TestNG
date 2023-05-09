package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class OrderDetailsPage extends BaseTools {

    private By thankYouMessage = By.xpath("//p[@class='woocommerce-thankyou-order-received']");
    private By orderNumber = By.xpath("//ul/li[@class='order']/strong");
    private By orderDate = By.xpath("//ul/li[@class='date']/strong");
    private By orderTotal = By.xpath("//ul/li[@class='total']/strong/span[contains(@class,'amount')]");
    private By paymentMethod = By.xpath("//ul/li[@class='method']/strong");
    private By customerEmail = By.xpath("//table[contains(@class,'customer_details')]/descendant::th[contains(text(),Email)]/following-sibling::td");
    private By customerPhone = By.xpath("//table[contains(@class,'customer_details')]/descendant::th[contains(text(),'Telephone')]/following-sibling::td");

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyThankYouMessage() {
        String expectedMessage = "Thank you. Your order has been received.";
        String actualMessage = getElementText(thankYouMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    public void verifyOrderAndCustomerDetailsVisibility() {
        Assert.assertTrue(isElementVisible(orderNumber));
        Assert.assertTrue(isElementVisible(orderDate));
        Assert.assertTrue(isElementVisible(orderTotal));
        Assert.assertTrue(isElementVisible(paymentMethod));
        Assert.assertTrue(isElementVisible(customerEmail));
        Assert.assertTrue(isElementVisible(customerPhone));
    }

    public String getTotalPrice() {
        return getElementText(orderTotal);
    }

    public String getPaymentMethod() {
        return getElementText(paymentMethod);
    }

}
