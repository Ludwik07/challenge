package selenium;

import base.basePageFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericObjectFunctions implements basePageFunctions {

    public static WebDriver driver;
    public static WebDriverWait driverWait;
    protected int defaultWaitTime;


    /**
     * Constructor picking default timeout
     *
     * @param driver Selenium webDriver
     */
    public GenericObjectFunctions(WebDriver driver) {
        this.driver = driver;
        this.defaultWaitTime = 20;
    }

    /**
     * Setter for driver
     *
     * @param driver Selenium webDriver
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Finds an HTML elements by any possible locator types
     *
     * @param locator locator string for web elements
     * @return WebElement reference object OR null if not able to get element
     */
    public WebElement getElement(String locator) {
        if (!locator.isEmpty()) {
            try {
                return driver.findElement(By.id(locator));
            } catch (Exception ex) {
            }
            try {
                return driver.findElement(By.xpath(locator));
            } catch (Exception ex) {
            }
            try {
                return driver.findElement(By.className(locator));
            } catch (Exception ex) {
            }
            try {
                return driver.findElement(By.cssSelector(locator));
            } catch (Exception ex) {
            }
            try {
                return driver.findElement(By.name(locator));
            } catch (Exception ex) {
            }
            try {
                return driver.findElement(By.tagName(locator));
            } catch (Exception ex) {
            }
            try {
                return driver.findElement(By.linkText(locator));
            } catch (Exception ex) {
            }
            try {
                return driver.findElement(By.partialLinkText(locator));
            } catch (Exception ex) {
            }
        }
        return null;
    }

    /**
     * Wait for an element to be clickable
     *
     * @param element  WebElement object reference
     * @param waitTime integer wait time in seconds
     * @return
     */
    public boolean waitForElementClickable(WebElement element, int waitTime) {
        try {
            setWaitTime(waitTime);
            driverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }

    /**
     * @param waitTime set a time for explicit waits
     */
    public void setWaitTime(int waitTime) {
        if (waitTime > 0) {
            driverWait = new WebDriverWait(driver, 0, waitTime * 1000);
        }
    }

    /**
     * @param locator  webelement locator string
     * @param waitTime time to wait for element to exist
     * @return true or false
     * @throws InterruptedException
     */
    public boolean waitForElementToExist(String locator, int waitTime) throws InterruptedException {
        try {
            setWaitTime(waitTime);
            driverWait.until(ExpectedConditions.visibilityOf(getElement(locator)));
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }

    /**
     * @param element  webelement
     * @param waitTime time to wait for element to exist
     * @return true or false
     * @throws InterruptedException
     */
    public boolean waitForElementToExist(WebElement element, int waitTime) throws InterruptedException {
        try {
            setWaitTime(waitTime);
            driverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }

    /**
     * @param element   selenium web element
     * @param value     value to be contained within a given attribute
     * @param attribute attribute to be asserted
     * @return true or false
     */
    public boolean waitForElementToContain(WebElement element, String attribute, String value) {
        return waitForElementToContain(element, value, attribute, defaultWaitTime);
    }

    /**
     * @param element   selenium web element
     * @param value     value to be contained within a given attribute
     * @param attribute attribute to be asserted
     * @param waitTime  time to wait for attribute to contain value
     * @return true or false
     */
    public boolean waitForElementToContain(WebElement element, String value, String attribute, int waitTime) {
        try {
            setWaitTime(waitTime);
            driverWait.until(ExpectedConditions.attributeContains(element, attribute, value));
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }

}
