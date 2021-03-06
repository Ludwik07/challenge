package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface basePageFunctions {

    void setDriver(WebDriver driver);
    WebElement getElement(String locator);
    void setWaitTime(int waitTime);
}
