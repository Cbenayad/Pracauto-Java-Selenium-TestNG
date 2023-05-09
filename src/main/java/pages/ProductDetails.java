package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductDetails extends BaseTools {
    private String pageUrl = "https://practice.automationtesting.in/product/mastering-javascript/";
    private By productTitle = By.xpath("//h1[contains(@class,'product_title')]");
    private By productPrice = By.xpath("//p[@class='price']/span[contains(@class,'amount')]");
    private By productQuantity = By.xpath("//div[@class='quantity']/input[@title='Qty']");
    private By productDescription = By.xpath("//h2[contains(text(),'Product Description')]/following-sibling::div/descendant::p");
    private By addToBasketButton = By.xpath("//button[contains(text(),'Add to basket')]");
    private By viewBasketButton = By.xpath("//a[contains(text(),'View Basket')]");
    private By successMessage = By.xpath("//div[@class='woocommerce-message']");
    private By cartCounter= By.xpath("//li[@id='wpmenucartli']/descendant::span[contains(@class,'cartcontents')]");
    private By cartAmount = By.xpath("//li[@id='wpmenucartli']/descendant::span[@class='amount']");
    public String title = getProductTitle();
    public String price = getPrice(productPrice);
    public String prodQuantity;
    public float total;

    public ProductDetails(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyPageUrl() {
        Assert.assertEquals(getCurrentUrl(), pageUrl);
    }

    public void verifyProductDetailsVisibility() {
        Assert.assertTrue(isElementVisible(productTitle));
        Assert.assertTrue(isElementVisible(productPrice));
        Assert.assertTrue(isElementVisible(productQuantity));
        Assert.assertTrue(isElementVisible(productDescription));
        Assert.assertTrue(isElementVisible(addToBasketButton));
    }

    public void addProduct(String quantity) {
        typeText(productQuantity, quantity);
        clickOnElement(addToBasketButton, "ADD TO BASKET button");
        prodQuantity = getProductQuantity();
        total = getProductTotal(price, prodQuantity);
    }

    public String getProductTitle() {
        return getElementText(productTitle);
    }

    public String getProductQuantity() {
        return getInputValue(productQuantity);
    }

    public float getProductTotal(String prodPrice, String prodQuantity) {
        float productPrice = Float.parseFloat(prodPrice);
        float productQuantity = Float.parseFloat(prodQuantity);
        float productTotal = productPrice * productQuantity;
        return productTotal;
    }

    public void verifyViewBtnAndSuccessMessage(String quantity) {
        Assert.assertTrue(isElementVisible(viewBasketButton));
        String expectedSuccessMessage;
        if (quantity.equals("1")) {
            expectedSuccessMessage = "“" + getProductTitle() + "” has been added to your basket.";
        } else {
            expectedSuccessMessage = quantity + " × “" + getProductTitle() + "” have been added to your basket.";

        }
        String actualSuccessMessage = getElementText(successMessage);
        Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage));
    }

    public BasketPage clickOnViewButton() {
        clickOnElement(viewBasketButton, "VIEW BASKET button");
        return new BasketPage(driver);
    }

    public void verifyProductNotAdded(String quantity){
        String actualCounterValue = getElementText(cartCounter);
        String actualAmountValue = getPrice(cartAmount);
        Assert.assertEquals(actualCounterValue,"0 Items");
        Assert.assertEquals(actualAmountValue,"0.00");
    }

}
