package testsClasses;

import config.SetupTearDown;
import org.testng.annotations.Test;
import pages.*;

public class LogoPresenceVerificationTest extends SetupTearDown {

    @Test
    public void logoPresenceAndRedirection(){
        // Open main page
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.openPage();

        // click on logo image from all pages
        welcomePage.clickLogoImage();

    }
}
