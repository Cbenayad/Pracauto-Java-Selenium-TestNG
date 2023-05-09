package pages;

import config.BaseTools;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BasketPage extends BaseTools {
    private String pageUrl = "https://practice.automationtesting.in/basket/";
    private By productName = By.xpath("//td[@data-title='Product']/a");
    private By productPrice = By.xpath("//td[@data-title='Price']/span[contains(@class,'amount')]");
    private By productQuantity = By.xpath("//td[@data-title='Quantity']/descendant::input");
    private By productTotal = By.xpath("//td[@data-title='Total']/span[contains(@class,'amount')]");
    private By productSubtotal = By.xpath("//td[@data-title='Subtotal']/span[contains(@class,'amount')]");
    private By productTax = By.xpath("//td[@data-title='Tax' or @data-title='Roaming Tax' ]/span[contains(@class,'amount')]");
    private By totalWithTax = By.xpath("//td[@data-title='Total']/strong/span[contains(@class,'amount')]");
    private By checkoutButton = By.xpath("//a[contains(@class,'checkout-button')]");
    private By removeProduct = By.xpath("//td[@class='product-remove']/a[@title='Remove this item']");
    private By returnToShopBtn = By.xpath("//a[contains(text(),'Return To Shop')]");
    private By successMessage = By.xpath("//div[@class='woocommerce-message']");
    private By quantityInput = By.xpath("//div[@class='quantity']/input");
    private By updateBasketBtn = By.xpath("//input[@name='update_cart']");
    public String subtotal = getProductsSubtotal();
    public String tax = getProductTax();
    public String total = getTotalWithTax();
    public String title = getTitle();

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyPageUrl() {
        Assert.assertEquals(getCurrentUrl(), pageUrl);
    }

    public String getTitle() {
        return getElementText(productName);
    }
    public String getQuantity() {
        return getInputValue(productQuantity);
    }

    public float getTotal() {
        String priceTotalWithSymbol = getElementText(productTotal);
        String total = StringUtils.substringAfter(priceTotalWithSymbol, "₹");
        String totalWithoutComma = total.replace(",", "");
        System.out.println(Float.parseFloat(totalWithoutComma));
        return Float.parseFloat(totalWithoutComma);
    }

    public String getProductsSubtotal() {
        return getElementText(productSubtotal);
    }

    public String getProductTax() {
        return getElementText(productTax);
    }

    public String getTotalWithTax() {
        return getElementText(totalWithTax);
    }

    public void verifyProductDetailsVisibility() {
        Assert.assertTrue(isElementVisible(productName));
        Assert.assertTrue(isElementVisible(productPrice));
        Assert.assertTrue(isElementVisible(productQuantity));
        Assert.assertTrue(isElementVisible(productTotal));
    }

    public void verifyProductDetailsAreAsExpected(ProductDetails productDetails) {
        //ProductDetails productDetails = new ProductDetails(driver, log);
        Assert.assertEquals(getTitle(), productDetails.title);
        Assert.assertEquals(getPrice(productPrice), productDetails.price);
        Assert.assertEquals(getQuantity(), productDetails.prodQuantity);
        Assert.assertEquals(getTotal(), productDetails.total);
    }

    public CheckoutPage clickCheckoutBtn() {
        clickOnElement(checkoutButton, "PROCEED TO CHECKOUT button");
        return new CheckoutPage(driver);
    }

   /* public void verifyProductDetails(ShopPage shopPage) {
        Assert.assertEquals(getTitle(), shopPage.title);
        Assert.assertEquals(getPrice(productPrice), shopPage.price);

    }*/

    public void clickOnRemoveButton() {
        clickOnElement(removeProduct, "remove product button");
    }

    public void verifyProductIsDeleted() {
        // verify success message
        String actualMessage = getElementText(successMessage);
        String expectedMessage = title + " removed.";
        Assert.assertTrue(actualMessage.contains(expectedMessage));
        // verify RETURN TO SHOP button is displayed
        Assert.assertTrue(isElementVisible(returnToShopBtn));
    }

    public void updateProductQuantity(String quantity){
        typeText(quantityInput, quantity);
        clickOnElement(updateBasketBtn, "UPDATE BASKET button");
    }

    public void verifyProductUpdated(String quantity){
        // verify success message
        String actualMessage = getElementText(successMessage);
        String expectedMessage = "Basket updated.";
        Assert.assertTrue(actualMessage.contains(expectedMessage));
        // verify product quantity change
        Assert.assertEquals(getQuantity(),quantity);
    }
}
