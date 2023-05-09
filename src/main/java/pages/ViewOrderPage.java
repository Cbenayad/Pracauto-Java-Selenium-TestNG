package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ViewOrderPage extends BaseTools {

    private By orderNumber = By.xpath("//mark[@class='order-number']");
    private By orderDate = By.xpath("//mark[@class='order-date']");
    private By orderStatus = By.xpath("//mark[@class='order-status']");
    private By productName = By.xpath("//td[@class='product-name']/a");
    private By productSubtotal = By.xpath("//th[contains(text(),'Subtotal')]/following-sibling::td/span");
    private By productTax = By.xpath("//th[contains(text(),'Roaming Tax')]/following-sibling::td/span");
    private By paymentMethod = By.xpath("//th[contains(text(),'Payment Method')]/following-sibling::td");
    private By productTotalPrice = By.xpath("//th[contains(text(),'Total')]/following-sibling::td/span");
    private By customerEmail = By.xpath("//th[contains(text(),'Email')]/following-sibling::td");
    private By customerPhone = By.xpath("//th[contains(text(),'Telephone')]/following-sibling::td");
    private By customerAddress = By.xpath("//header[@class='title']/following-sibling::address");

    public ViewOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyOrderDetails() {
        Assert.assertTrue(isElementVisible(orderNumber));
        Assert.assertTrue(isElementVisible(orderDate));
        Assert.assertTrue(isElementVisible(orderStatus));
    }

    public void verifyProductDetails() {
        Assert.assertTrue(isElementVisible(productName));
        Assert.assertTrue(isElementVisible(productSubtotal));
        Assert.assertTrue(isElementVisible(productTax));
        Assert.assertTrue(isElementVisible(paymentMethod));
        Assert.assertTrue(isElementVisible(productTotalPrice));
    }

    public void verifyCustomerDetails(){
        Assert.assertTrue(isElementVisible(customerEmail));
        Assert.assertTrue(isElementVisible(customerAddress));
    }
}
