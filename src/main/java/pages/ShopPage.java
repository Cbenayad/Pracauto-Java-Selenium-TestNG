package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class ShopPage extends BaseTools {

    private String pageUrl = "https://practice.automationtesting.in/shop/";
    private By productImage = By.xpath("//h3[contains(text(),'Mastering JavaScript')]/preceding-sibling::img");
    private By addToBasketButton = By.xpath("//a[contains(text(),'Add to basket')]");
    private By viewBasketLink = By.xpath("//a[contains(text(),'View Basket')]");
    private By productTitle = By.xpath("//h3[contains(text(),'Mastering JavaScript')]");
    private By productPrice = By.xpath("//h3[contains(text(),'Mastering JavaScript')]/following-sibling::span/span");
    private By leftSlider = By.xpath("//span[contains(@class,'ui-slider-handle') and contains(@style,'left: 0%')]");
    private By rightSlider = By.xpath("//span[contains(@class,'ui-slider-handle') and contains(@style,'left: 100%')]");
    private By sliderBar = By.xpath("//div[contains(@class,'ui-slider-horizontal')]");
    private By sliderMinimumPrice = By.xpath("//div[@class='price_label']/span[@class='from']");
    private By sliderMaximumPrice = By.xpath("//div[@class='price_label']/span[@class='to']");
    private By filterButton = By.xpath("//button[contains(text(),'Filter')]");
    private By prices = By.xpath("//span[@class='price']/child::span | //span[@class='price']/child::ins/span");
    private By categoryItems = By.xpath("//li[contains(@class,'cat-item')]");
    private By productItems = By.xpath("//ul[contains(@class,'products')]/li");

    private By getPriceSpan(int i) {
        return By.xpath("(//span[@class='price']/child::span | //span[@class='price']/child::ins/span)[" + i + "]");
    }

    private By getCategoryItem(int i) {
        return By.xpath("(//li[contains(@class,'cat-item')]/a)[" + i + "]");
    }

    private By getProductItem(int i) {
        return By.xpath("(//ul[contains(@class,'products')]/li)[" + i + "]");
    }
    // public String title = getTitle();
    //  public String price = getPrice(productPrice);

    public ShopPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    public ProductDetails clickOnProductImage() {
        checkElementVisibility(productImage);
        clickOnElement(productImage, "product image");
        return new ProductDetails(driver);
    }

    public ProductDetails clickOnProductTitle() {
        checkElementVisibility(productTitle);
        clickOnElement(productTitle, "product title");
        return new ProductDetails(driver);
    }

    public ProductDetails clickOnProductPrice() {
        checkElementVisibility(productPrice);
        clickOnElement(productPrice, "product price");
        return new ProductDetails(driver);
    }

    public void clickOnAddToBasketBtn() {
        checkElementVisibility(addToBasketButton);
        clickOnElement(addToBasketButton, "ADD TO BASKET button");
    }

    public BasketPage clickOnViewBasketLink() {
        checkElementVisibility(viewBasketLink);
        clickOnElement(viewBasketLink, "View Basket");
        return new BasketPage(driver);
    }

    public String getTitle() {
        return getElementText(productTitle);
    }

    public void moveSlider(WebElement slider, int value, int minValue, int maxValue) {
        // Calculate the percentage of the slider to move based on the value
        double range = maxValue - minValue;
        double distance;
        if (slider.getAttribute("style").equals("left: 0%;")) {
            distance = value - minValue;
        } else {
            distance = value - maxValue;
        }
        double percent = distance / range;
        // Calculate the x offset for the slider based on the percentage
        int xOffset = (int) (percent * find(sliderBar).getSize().getWidth());
        // Use Actions class to move the slider to the desired value
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, xOffset, 0).build().perform();
    }

    public void filterProductsByPrice(int minPrice, int maxPrice) {
        int sliderMinPrice = Integer.parseInt(getPrice(sliderMinimumPrice));
        int sliderMaxPrice = Integer.parseInt(getPrice(sliderMaximumPrice));
        moveSlider(find(leftSlider), minPrice, sliderMinPrice, sliderMaxPrice);
        moveSlider(find(rightSlider), maxPrice, sliderMinPrice, sliderMaxPrice);
        clickOnElement(filterButton, "FILTER button");
    }

    public void verifyFilteredProducts(int minPrice, int maxPrice) {
        int pricesNbr = driver.findElements(prices).size();

        for (int i = 1; i <= pricesNbr; i++) {
            String priceText = getPrice(getPriceSpan(i)).replace(".00", "");
            if (Integer.parseInt(priceText) >= minPrice && Integer.parseInt(priceText) <= maxPrice) {
                // verify only products between max & min prices are displayed
                Assert.assertTrue(isElementVisible(getPriceSpan(i)));
                System.out.println(priceText);
            }
        }
    }

    public void filterProductsByCategory() {
        int categoryItemsNbr = driver.findElements(categoryItems).size();

        for (int i = 1; i <= categoryItemsNbr; i++) {
            // click on category item link
            clickOnElement(getCategoryItem(i), "category item");
            String categoryName = getElementText(getCategoryItem(i)).toLowerCase();
            // verify only products with selected category are displayed
            int productsNbr = driver.findElements(productItems).size();
            for (int j = 1; j <= productsNbr; j++) {
                if (find(getProductItem(j)).getAttribute("class").contains("product_cat-" + categoryName)) {
                    Assert.assertTrue(isElementVisible(getProductItem(j)));
                }
            }
        }
    }

}
