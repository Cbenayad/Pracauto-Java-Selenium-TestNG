package pages;

import config.BaseTools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class WelcomePage extends BaseTools {
    private String pageUrl = "https://practice.automationtesting.in/";
    private By myAccountLink = By.xpath("//a[text()='My Account']");
    private By shopLink = By.xpath("//a[text()='Shop']");
    private By testCasesLink = By.xpath("//a[text()='Test Cases']");
    private By shoppingCartLink = By.xpath("//li[@id='wpmenucartli']/child::a");
    private By navbarLinks = By.xpath("//ul[@id='main-nav']/descendant::a");
    private By logoImage = By.xpath("//img[@title='Automation Practice Site']");
    private By iconsLinks = By.xpath("(//li[contains(@class,'social-link-item')]/child::a)");

    private By getNavbarLink(int i) {
        return By.xpath("(//ul[@id='main-nav']/descendant::a)[" + i + "]");
    }

    private By getIconLink(int i) {
        return By.xpath("(//li[contains(@class,'social-link-item')]/child::a)[" + i + "]");
    }


    public WelcomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Open WelcomePage with it's url
     */
    public void openPage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    /**
     * verify page URL
     */

    public void verifyWelcomePageUrl() {
        Assert.assertEquals(getCurrentUrl(), pageUrl);
    }

    /**
     * Open Login Page by clicking on My Account link
     */
    public LoginPage clickMyAccountLink() {
        log.info("Clicking Form Authentication link on Welcome Page");
        clickOnElement(myAccountLink, "My Account link");
        return new LoginPage(driver);
    }

    /**
     * Open Shop Page by clicking on Shop link
     */
    public ShopPage clickShopLink() {
        log.info("Clicking Shop link on Welcome Page");
        clickOnElement(shopLink, "Shop link");
        return new ShopPage(driver);
    }

    /**
     * Open Test Cases Page by clicking on Test Cases link
     */
    public TestCasesPage clickTestCasesLink() {
        log.info("Clicking Test Cases link on Welcome Page");
        clickOnElement(testCasesLink, "Test Cases link");
        return new TestCasesPage(driver);
    }

    /**
     * Open Basket Page by clicking on Basket link
     */
    public BasketPage clickBasketLink() {
        log.info("Clicking Basket link on Welcome Page");
        clickOnElement(shoppingCartLink, "Basket link");
        return new BasketPage(driver);
    }

    /**
     * Redirection to WelcomePage by clicking on logo 'AT'
     */
    public void clickLogoImage() {

        int navbarLinksNbr = driver.findElements(navbarLinks).size();

        for (int i = 1; i <= navbarLinksNbr; i++) {
            String navbarLinkText = getElementText(getNavbarLink(i));
            if (!navbarLinkText.equals("AT Site") && !navbarLinkText.equals("Demo Site")) {
                // click on navbar link
                clickOnElement(getNavbarLink(i), navbarLinkText+" link");
                // verify logo image is displayed
                Assert.assertTrue(isElementVisible(logoImage));
                // click on logo image
                clickOnElement(logoImage, "logo image");
                // verify we are redirected to welcome page
                Assert.assertEquals(getCurrentUrl(), pageUrl);
            }
        }
    }

    public void closeAdBlocker() {
        switchToNextTab(driver);
        closeNextTab(driver);
        switchToPreviousTab(driver);
    }

    public void clickOnSocialMediaIcons() throws Exception {
        int nbr = driver.findElements(iconsLinks).size();

        for (int i = 1; i <= nbr; i++) {
            String iconHref = driver.findElement(getIconLink(i)).getAttribute("title").toLowerCase().replace("+", "");
            clickOnElement(getIconLink(i), "icon link");
            switchToNextTab(driver);
            System.out.println(iconHref);
            System.out.println(driver.getCurrentUrl());
            if ((iconHref.contains(driver.getCurrentUrl())) || (driver.getCurrentUrl().contains(iconHref))) {
                closeNextTab(driver);
                switchToPreviousTab(driver);
            } else {
                throw new Exception();
            }
        }
    }
}
