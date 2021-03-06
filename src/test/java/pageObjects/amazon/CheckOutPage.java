package pageObjects.amazon;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import selenium.GenericObjectFunctions;

public class CheckOutPage extends PageBase {

    final String AMZ_PAGE_TITLE="Amazon Sign-In";
    public CheckOutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void setFunctions() {

    }

    @Override
    public void assertPage() {
        System.out.println("Expected: '" + AMZ_PAGE_TITLE + "', Found: '" + driver.getTitle() + "'");
        Assert.assertEquals(driver.getTitle(), AMZ_PAGE_TITLE);
    }

    @Override
    public void assertPage(String param) {

    }


}
