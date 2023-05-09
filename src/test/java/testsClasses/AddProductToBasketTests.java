package testsClasses;

import config.Constants;
import config.SetupTearDown;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

public class AddProductToBasketTests extends SetupTearDown {
    @Parameters({"quantity"})
    @Test
    public void AddProductFromImage(String quantity) {
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(Constants.email, Constants.password);

        // open Shop page
        ShopPage shopPage = myAccountPage.clickShopLink();

        // click on product image
        ProductDetails productDetails = shopPage.clickOnProductImage();

        // verifications
        // page url as expected
        productDetails.verifyPageUrl();
        // product details are displayed
        productDetails.verifyProductDetailsVisibility();

        // click on ADD TO BASKET button
        productDetails.addProduct(quantity);

        // Verify VIEW BASKET button is displayed &  product added successfully message
        productDetails.verifyViewBtnAndSuccessMessage(quantity);

        // click on VIEW BASKET button
        BasketPage basketPage = productDetails.clickOnViewButton();

        // verifications
        // product details are visible ((Product, Price, Quantity, Total)
        basketPage.verifyProductDetailsVisibility();
        // all product details are as expected
        basketPage.verifyProductDetailsAreAsExpected(productDetails);
    }

    @Test
    public void AddProductViaAddButton() {
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(Constants.email, Constants.password);

        // open Shop page
        ShopPage shopPage = myAccountPage.clickShopLink();

        // click on ADD TO BASKET button
        shopPage.clickOnAddToBasketBtn();

        // click on View Basket link
        BasketPage basketPage = shopPage.clickOnViewBasketLink();

        // verifications
        // product details are visible ((Product, Price, Quantity, Total)
        basketPage.verifyProductDetailsVisibility();
        // all product details are as expected
         //basketPage.verifyProductDetails(shopPage);
    }

    @Parameters({"quantity"})
    @Test
    public void AddProductFromTitle(String quantity) {
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(Constants.email, Constants.password);

        // open Shop page
        ShopPage shopPage = myAccountPage.clickShopLink();

        // click on product title
        ProductDetails productDetails = shopPage.clickOnProductTitle();

        // verifications
        // page url as expected
        productDetails.verifyPageUrl();
        // product details are displayed
        productDetails.verifyProductDetailsVisibility();

        // click on ADD TO BASKET button
        productDetails.addProduct(quantity);

        // Verify VIEW BASKET button is displayed &  product added successfully message
        productDetails.verifyViewBtnAndSuccessMessage(quantity);

        // click on VIEW BASKET button
        BasketPage basketPage = productDetails.clickOnViewButton();

        // verifications
        // product details are visible ((Product, Price, Quantity, Total)
        basketPage.verifyProductDetailsVisibility();
        // all product details are as expected
        basketPage.verifyProductDetailsAreAsExpected(productDetails);
    }

    @Parameters({"quantity"})
    @Test
    public void AddProductFromPrice(String quantity) {
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(Constants.email, Constants.password);

        // open Shop page
        ShopPage shopPage = myAccountPage.clickShopLink();

        // click on product price
        ProductDetails productDetails = shopPage.clickOnProductPrice();

        // verifications
        // page url as expected
        productDetails.verifyPageUrl();
        // product details are displayed
        productDetails.verifyProductDetailsVisibility();

        // click on ADD TO BASKET button
        productDetails.addProduct(quantity);

        // Verify VIEW BASKET button is displayed &  product added successfully message
        productDetails.verifyViewBtnAndSuccessMessage(quantity);

        // click on VIEW BASKET button
        BasketPage basketPage = productDetails.clickOnViewButton();

        // verifications
        // product details are visible ((Product, Price, Quantity, Total)
        basketPage.verifyProductDetailsVisibility();
        // all product details are as expected
        basketPage.verifyProductDetailsAreAsExpected(productDetails);
    }
    @Parameters({"quantity"})
    @Test
    public void addProductWithZeroAsQuantity(String quantity){
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(Constants.email, Constants.password);

        // open Shop page
        ShopPage shopPage = myAccountPage.clickShopLink();

        // click on product image
        ProductDetails productDetails = shopPage.clickOnProductImage();

        // verifications
        // page url as expected
        productDetails.verifyPageUrl();
        // product details are displayed
        productDetails.verifyProductDetailsVisibility();

        // click on ADD TO BASKET button
        productDetails.addProduct(quantity);

        // Verify the product is not added
        productDetails.verifyProductNotAdded(quantity);
    }
}
