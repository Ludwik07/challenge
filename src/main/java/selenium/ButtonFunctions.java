package selenium;

import org.openqa.selenium.WebElement;

public class ButtonFunctions extends GenericObjectFunctions {

    public ButtonFunctions() {
        super(driver);
    }

    /**
     * @param element selenium web element reference
     * @param timeOut time for explicit wait
     * @throws Exception
     */
    public void waitAndClick(WebElement element, int timeOut) throws Exception {
        if (!waitForElementClickable(element, timeOut)){
            element.click();
        }else{
            throw new Exception("Element " + element + " not clickable");
        }

    }

    /**
     * @param element  selenium web element reference
     * @throws Exception
     */
    public void waitAndClick(WebElement element) throws Exception {
        waitAndClick(element, defaultWaitTime);
    }
}
