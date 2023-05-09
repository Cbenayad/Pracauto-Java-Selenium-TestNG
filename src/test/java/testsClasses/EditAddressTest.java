package testsClasses;

import config.Constants;
import config.SetupTearDown;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

public class EditAddressTest extends SetupTearDown {
    @Parameters({"firstName", "lastName", "email", "phone", "country", "address", "postCode", "city", "expectedMessage"})
    @Test
    public void editBillingAddress(String firstName, String lastName, String email, String phone, String country, String address, String city, String postCode, String expectedMsg) {
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(Constants.email, Constants.password);

        // click on addresses link
        AddressesPage addressesPage = myAccountPage.clickAddressesLink();

        // "Billing Address" & "Shipping Address" are displayed
        addressesPage.verifyTitlesAreDisplayed();

        // click on Edit button on billing address section
        BillingAddressPage billingAddressPage = addressesPage.clickOnEditBillingAddress();

        // verification
        billingAddressPage.verifyPageHeader();

        // fill billing address form
        billingAddressPage.FillBillingAddressForm(firstName, lastName, email, phone, country, address, postCode, city);

        // click on SAVE ADDRESS button
        MyAccountPage myAccountPage1 = billingAddressPage.clickOnSaveButton();

        // verify success message is as expected
        myAccountPage1.verifyAddressUpdateSuccessMessage(expectedMsg);

        // click on Addresses link
        AddressesPage addressesPage1 = myAccountPage1.clickAddressesLink();

        // verify billing address data is as expected
        addressesPage1.verifyBillingAddressData();

    }

    @Parameters({"firstName", "lastName", "email", "phone", "country", "address", "postCode", "city", "expectedMessage"})
    @Test
    public void editBillingAddressWithEmptyFields(String firstName, String lastName, String email, String phone, String country, String address, String city, String postCode, String expectedMsg) {
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(Constants.email, Constants.password);

        // click on addresses link
        AddressesPage addressesPage = myAccountPage.clickAddressesLink();

        // "Billing Address" & "Shipping Address" are displayed
        addressesPage.verifyTitlesAreDisplayed();

        // click on Edit button on billing address section
        BillingAddressPage billingAddressPage = addressesPage.clickOnEditBillingAddress();

        // verification
        billingAddressPage.verifyPageHeader();

        // fill billing address form
        billingAddressPage.FillBillingAddressForm(firstName, lastName, email, phone, country, address, postCode, city);

        // click on SAVE ADDRESS button
       billingAddressPage.clickOnSaveButton();

        // verify error message is as expected
        billingAddressPage.verifyErrorMessages(expectedMsg);
    }

    @Parameters({"firstName", "lastName", "country", "address", "postCode", "city", "expectedMessage"})
    @Test
    public void editShippingAddress(String firstName, String lastName, String country, String address, String city, String postCode, String expectedMsg) {
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(Constants.email, Constants.password);

        // click on addresses link
        AddressesPage addressesPage = myAccountPage.clickAddressesLink();

        // "Billing Address" & "Shipping Address" are displayed
        addressesPage.verifyTitlesAreDisplayed();

        // click on Edit button on shipping address section
        ShippingAddressPage shippingAddressPage = addressesPage.clickOnEditShippingAddress();

        // verification
        shippingAddressPage.verifyPageHeader();

        // fill shipping address form
        shippingAddressPage.FillShippingAddressForm(firstName, lastName, country, address, postCode, city);

        // click on SAVE ADDRESS button
        MyAccountPage myAccountPage1 = shippingAddressPage.clickOnSaveButton();

        // verify success message is as expected
        myAccountPage1.verifyAddressUpdateSuccessMessage(expectedMsg);

        // click on Addresses link
        AddressesPage addressesPage1 = myAccountPage1.clickAddressesLink();

        // verify shipping address data is as expected
        addressesPage1.verifyShippingAddressData();

    }

    @Parameters({"firstName", "lastName", "country", "address", "postCode", "city", "expectedMessage"})
    @Test
    public void editShippingAddressWithEmptyFields(String firstName, String lastName, String country, String address, String city, String postCode, String expectedMsg) {
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // Click on My Account link
        LoginPage loginPage = welcomePage.clickMyAccountLink();

        // execute log in
        MyAccountPage myAccountPage = loginPage.logIn(Constants.email, Constants.password);

        // click on addresses link
        AddressesPage addressesPage = myAccountPage.clickAddressesLink();

        // "Billing Address" & "Shipping Address" are displayed
        addressesPage.verifyTitlesAreDisplayed();

        // click on Edit button on shipping address section
        ShippingAddressPage shippingAddressPage = addressesPage.clickOnEditShippingAddress();

        // verification
        shippingAddressPage.verifyPageHeader();

        // fill shipping address form
        shippingAddressPage.FillShippingAddressForm(firstName, lastName, country, address, postCode, city);

        // click on SAVE ADDRESS button
        shippingAddressPage.clickOnSaveButton();

        // verify error message is as expected
        shippingAddressPage.verifyErrorMessages(expectedMsg);
    }

}
