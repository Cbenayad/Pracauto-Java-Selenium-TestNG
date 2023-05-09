package testsClasses;

import config.Constants;
import config.SetupTearDown;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

public class DeleteProductTest extends SetupTearDown {
    @Parameters({"quantity"})
    @Test
    public void deleteProductFromBasket(String quantity) {
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

        // click on remove product button
        basketPage.clickOnRemoveButton();

        // verify product is deleted successfully
        basketPage.verifyProductIsDeleted();
    }

}
