package pages;

import config.BaseTools;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BaseTools {
    private String pageUrl = "https://practice.automationtesting.in/test-cases/";

    public TestCasesPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Get page URL variable
     */
    public String getPageUrl() {
        return pageUrl;
    }
}
