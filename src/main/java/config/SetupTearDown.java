package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;

public class SetupTearDown {

    protected WebDriver driver;
    protected static final Logger log = LogManager.getLogger(BaseTools.class);

    @BeforeTest()
    public void setUp() {
        log.info("Create chrome driver");

        // Create driver using chromedriver.exe file
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        // Create driver using WebDriver manager
        WebDriverManager.chromedriver().setup();

        // add adBlocker extension
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/main/resources/adblocker_5_3_3_0.crx"));
        driver = new ChromeDriver(options);

        // maximize browser window
        driver.manage().window().maximize();
    }

    @AfterTest()
    public void tearDown() {
        log.info("Close driver");
        // Close browser
        driver.quit();
    }
}
