package testsClasses;

import config.Constants;
import config.SetupTearDown;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

public class ViewOrdersHistoryTest extends SetupTearDown {
    @Test
    public void viewOrdersHistory() {

        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(Constants.email, Constants.password);

        // click on Orders link
        OrdersHistoryPage ordersHistoryPage = myAccountPage.clickOrdersLink();

        // verifications
        ordersHistoryPage.verifyOrdersDetailsVisibility();

        // click on VIEW button
        ViewOrderPage viewOrderPage = ordersHistoryPage.clickOnViewOrderButton();

        // verifications
        viewOrderPage.verifyOrderDetails();
        viewOrderPage.verifyProductDetails();
        viewOrderPage.verifyCustomerDetails();

    }
    @Parameters({"email","password"})
    @Test
    public void foundNoOrdersHistory(String email,String password){
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(email,password);

        // click on Orders link
        OrdersHistoryPage ordersHistoryPage = myAccountPage.clickOrdersLink();

        // verifications
       ordersHistoryPage.verifyMessage();
       ordersHistoryPage.verifyGoShopBtnVisibility();
    }
}
