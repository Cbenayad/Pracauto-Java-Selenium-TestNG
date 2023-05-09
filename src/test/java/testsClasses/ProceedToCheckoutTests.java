package testsClasses;

import config.Constants;
import config.SetupTearDown;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

public class ProceedToCheckoutTests extends SetupTearDown {

    @Parameters({"firstName", "lastName", "email", "phone", "country", "address", "postCode", "city"})
    @Test
    public void paymentByDirectBankTransfer(String firstName, String lastName, String email, String phone, String country, String address, String city, String postCode) {
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

        // click on PROCEED TO CHECKOUT
        CheckoutPage checkoutPage = basketPage.clickCheckoutBtn();

        // verifications
        // page url as expected
        checkoutPage.verifyPageUrl();
        // "Billing Details" & "Your order" titles &  payment options are displayed
        checkoutPage.verifySectionTitlesVisibility();
        // subtotal & roaming tax and final total are as expected
        checkoutPage.verifyProductDetails(basketPage);

        // fill the form with customer details
        checkoutPage.fillCustomerDetails(firstName, lastName, email, phone, country, address, postCode, city);

        // select check payment option
        checkoutPage.selectDirectBankTransferPaymentOption();

        // click on PLACE ORDER button
        OrderDetailsPage orderDetails = checkoutPage.clickOnPlaceOrderButton();

        // verifications
        // get thank you message
        orderDetails.verifyThankYouMessage();
        // order details and customer details are displayed
        orderDetails.verifyOrderAndCustomerDetailsVisibility();

    }
    @Parameters({"firstName", "lastName", "email", "phone", "country", "address", "postCode", "city"})
    @Test
    public void paymentByCheck(String firstName, String lastName, String email, String phone, String country, String address, String city, String postCode) {
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

        // click on PROCEED TO CHECKOUT
        CheckoutPage checkoutPage = basketPage.clickCheckoutBtn();

        // verifications
        // page url as expected
        checkoutPage.verifyPageUrl();
        // "Billing Details" & "Your order" titles &  payment options are displayed
        checkoutPage.verifySectionTitlesVisibility();
        // subtotal & roaming tax and final total are as expected
        checkoutPage.verifyProductDetails(basketPage);

        // fill the form with customer details
        checkoutPage.fillCustomerDetails(firstName, lastName, email, phone, country, address, postCode, city);

        // select check payment option
        checkoutPage.selectCheckPaymentOption();

        // click on PLACE ORDER button
        OrderDetailsPage orderDetails = checkoutPage.clickOnPlaceOrderButton();

        // verifications
        // get thank you message
        orderDetails.verifyThankYouMessage();
        // order details and customer details are displayed
        orderDetails.verifyOrderAndCustomerDetailsVisibility();

    }

    @Parameters({"firstName", "lastName", "email", "phone", "country", "address", "postCode", "city"})
    @Test
    public void paymentByCash(String firstName, String lastName, String email, String phone, String country, String address, String city, String postCode) {
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

        // click on PROCEED TO CHECKOUT
        CheckoutPage checkoutPage = basketPage.clickCheckoutBtn();

        // verifications
        // page url as expected
        checkoutPage.verifyPageUrl();
        // "Billing Details" & "Your order" titles &  payment options are displayed
        checkoutPage.verifySectionTitlesVisibility();
        // subtotal & roaming tax and final total are as expected
        checkoutPage.verifyProductDetails(basketPage);

        // fill the form with customer details
        checkoutPage.fillCustomerDetails(firstName, lastName, email, phone, country, address, postCode, city);

        // select cash on delivery payment option
        checkoutPage.selectCashPaymentOption();

        // click on PLACE ORDER button
        OrderDetailsPage orderDetails = checkoutPage.clickOnPlaceOrderButton();

        // verifications
        // get thank you message
        orderDetails.verifyThankYouMessage();
        // order details and customer details are displayed
        orderDetails.verifyOrderAndCustomerDetailsVisibility();

    }
}
