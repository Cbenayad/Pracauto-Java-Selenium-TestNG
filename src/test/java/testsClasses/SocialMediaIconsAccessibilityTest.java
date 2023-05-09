package testsClasses;

import config.SetupTearDown;
import org.testng.annotations.Test;
import pages.*;

public class SocialMediaIconsAccessibilityTest extends SetupTearDown {
    @Test
    public void AccessSocialMedia() throws Exception {
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();
        // Click on Shop link
        welcomePage.clickShopLink();

        // Click on social media icons from Shop page
        welcomePage.closeAdBlocker();
        welcomePage.clickOnSocialMediaIcons();

        // Click on My Account link
        welcomePage.clickMyAccountLink();

        // Click on social media icons from My Account page
        welcomePage.clickOnSocialMediaIcons();

        // Click on Test Cases link
        welcomePage.clickTestCasesLink();

        // Click on social media icons from Test Cases page
        welcomePage.clickOnSocialMediaIcons();
    }
}
