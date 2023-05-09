package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class OrdersHistoryPage extends BaseTools {
    private By orderNumber = By.xpath("//th[@class='order-number']/span");
    private By orderDate = By.xpath("//th[@class='order-date']/span");
    private By orderStatus = By.xpath("//th[@class='order-status']/span");
    private By orderTotal = By.xpath("//th[@class='order-total']/span");
    private By viewButton = By.xpath("//td[@class='order-actions']/a[contains(text(),'View')]");
    private By message = By.xpath("//div[contains(@class,'woocommerce-info')]");
    private By goShopButton =By.xpath("//a[contains(text(),'Go Shop')]");

    public OrdersHistoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyOrdersDetailsVisibility(){
        // (number,date,status,total) table headers are displayed
        Assert.assertTrue(isElementVisible(orderNumber));
        Assert.assertTrue(isElementVisible(orderDate));
        Assert.assertTrue(isElementVisible(orderStatus));
        Assert.assertTrue(isElementVisible(orderTotal));
    }
    public ViewOrderPage clickOnViewOrderButton() {
        clickOnElement(viewButton, "VIEW button");
        return new ViewOrderPage(driver);
    }

    public void verifyMessage(){
        String actualMessage = getElementText(message);
        String expectedMessage = "No order has been made yet.";
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    public void verifyGoShopBtnVisibility(){
        Assert.assertTrue(isElementVisible(goShopButton));
    }

}
