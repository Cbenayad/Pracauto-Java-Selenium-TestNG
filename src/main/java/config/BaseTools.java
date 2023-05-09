package config;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class BaseTools extends SetupTearDown{
    private int timeout = 60;

    /**
     * Open the page with given URL
     *
     * @param url
     */
    protected void openUrl(String url) {
        driver.get(url);
    }


    /**
     * Get URL of current page from the browser
     *
     * @return the current page URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get current page title
     *
     * @return the current page title
     */
    public String getCurrentTitle() {
        return driver.getTitle();
    }

    /**
     * Find an element using given locator
     *
     * @param locator
     * @return a web element
     */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Check the visibility of a web element
     *
     * @param elementLocator
     * @return a web element
     */
    protected WebElement checkElementVisibility(By elementLocator) {
        WebElement element;
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

        } catch (TimeoutException t) {
            throw new NoSuchElementException("Element is invisible!");
        }
        return element;
    }

    /**
     * Check if a web element is clickable
     *
     * @param elementLocator
     * @return a web element
     */
    protected WebElement checkElementClickable(By elementLocator) {
        WebElement element;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(elementLocator));

        } catch (TimeoutException t) {
            throw new InvalidElementStateException("Element is not clickable!");
        }
        return element;
    }

    /**
     * Click on element with given locator when its visible & clickable
     *
     * @param elementLocator
     */
    protected void clickOnElement(By elementLocator, String elementName) {
        log.info("Click on " + elementName);
        checkElementVisibility(elementLocator);
        checkElementClickable(elementLocator);
        find(elementLocator).click();
    }

    /**
     * Type text in an input field when its visible
     *
     * @param elementLocator
     */
    protected void typeText(By elementLocator, String text) {
        log.info("Typing " + text);
        checkElementVisibility(elementLocator);
        find(elementLocator).clear();
        find(elementLocator).sendKeys(text);
    }

    /**
     * Verification if an element is visible on the page
     *
     * @param element
     * @return a boolean value
     */
    protected boolean isElementVisible(By element) {
        return find(element).isDisplayed();
    }

    /**
     * Return element text
     *
     * @param element
     * @return text of web element
     */
    protected String getElementText(By element) {
        checkElementVisibility(element);
        return find(element).getText();
    }

    /**
     * Return input value
     *
     * @params element
     * @return value of input field
     */
    protected String getInputValue(By element) {
        checkElementVisibility(element);
        return find(element).getAttribute("value");
    }

    /**
     * Manage browser tabs
     *
     * @params driver
     */
    protected void switchToNextTab(WebDriver driver) {
        ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(allTabs.get(1));
    }

    protected void switchToPreviousTab(WebDriver driver) {
        ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(allTabs.get(0));
    }

    protected void closeNextTab(WebDriver driver) {
        ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(allTabs.get(1)).close();
    }

    /**
     * Stop test for an amount of time
     *
     * @params time
     */
    protected void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get product price
     *
     * @params element
     * @return price
     */
    protected String getPrice(By element) {
        String priceWithSymbol = getElementText(element);
        String price = StringUtils.substringAfter(priceWithSymbol, "₹");
        return price;
    }

}



