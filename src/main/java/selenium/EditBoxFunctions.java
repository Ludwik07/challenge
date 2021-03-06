package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditBoxFunctions extends GenericObjectFunctions {

    public EditBoxFunctions() {
        super(driver);
    }

    /** Waits for an element and types a given text
     * @param locator web element locator string
     * @param text string to be typed into the editbox element
     */
    public void type(String locator, String text) throws Exception {
        this.type(locator, text, defaultWaitTime);
    }

    /** Waits for an element and types a given text
     * @param locator web element locator string
     * @param text string to be typed into the editbox element
     * @param tab perform a tab after type text if true
     */
    public void type(String locator, String text, boolean tab) throws Exception {
        this.type(locator, text, defaultWaitTime);
    }

    /** Waits for an element and types a given text
     * @param locator web element locator string
     * @param text string to be typed into the editbox element
     * @param timeout time to wait for element to be visible and enabled
     */
    public void type(String locator, String text, int timeout) throws Exception {
        WebElement element=null;
        try {
            element = getElement(locator);
        } catch (Exception e) {
            throw new Exception("Element '" + locator + "' does not exist");
        }
        type(element, text);
    }

    /** Waits for an element and types a given text
     * @param element web element reference
     * @param text string to be typed into the editbox element

     */
    public void type(WebElement element, String text) throws Exception {
            if (element != null) {
                element.sendKeys(text);
            } else {
                throw new Exception("Element '" + element + "' is null");
            }
    }

    public void clear(WebElement element) throws Exception {
        if (element != null) {
            element.clear();
        } else {
            throw new Exception("Element does not exist");
        }
    }
}
