package testsClasses;

import config.Constants;
import config.SetupTearDown;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

public class UpdateProductQuantityTest extends SetupTearDown {
    @Parameters({"quantity"})
    @Test
    public void updateProduct(String quantity) {
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

        // update product quantity
        basketPage.updateProductQuantity(quantity);

        // verify product is updated successfully
        basketPage.verifyProductUpdated(quantity);
    }
}
