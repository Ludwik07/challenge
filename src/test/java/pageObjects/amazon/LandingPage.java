package pageObjects.amazon;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import selenium.EditBoxFunctions;
import selenium.GenericObjectFunctions;

public class LandingPage extends PageBase {

    protected EditBoxFunctions editBox = null;
    final String AMZ_PAGE_TITLE = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
    final String AMZ_SEARCH_BOX = "twotabsearchtextbox";
    final String AMZ_SEARCH_BTN = "nav-search-submit-button";
    final String AMZ_SIGN_IN_BTN = "nav-link-accountList-nav-line-1";
    final String AMZ_SIGN_IN_NEW_CTMR_BTN = "//*[@id='nav-flyout-ya-newCust']/a";

    @FindBy(xpath = AMZ_SIGN_IN_NEW_CTMR_BTN)
    WebElement AmzSingInNewCtmrBtn;

    @FindBy(id = AMZ_SIGN_IN_BTN)
    WebElement AmzSingInBtn;

    @FindBy(id = AMZ_SEARCH_BOX)
    WebElement AmzSearchBox;

    @FindBy(id = AMZ_SEARCH_BTN)
    WebElement AmzSearchButton;

    @Override
    public void setFunctions() {
        try {
            editBox = new EditBoxFunctions();
        } catch (Exception ex) {
            System.out.println("Error while setting test: " + ex);
        }
    }

    public LandingPage(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(super.driver, this);
        setFunctions();
    }

    public void search(String value) {
        try {
            editBox.type(AmzSearchBox, value);
            if (!obj.waitForElementToContain(AmzSearchBox, "value", value)) {
                Assert.fail("Fail: The element '" + AMZ_SEARCH_BOX + "' should contain '" + value + ". Instead it has: '" + AmzSearchBox.getText() + "'");
            }
            AmzSearchButton.click();
        } catch (Exception e) {
            Assert.fail("Failed to type " + value + " into element " + AMZ_SEARCH_BOX + ". Exception: " + e);
        }

    }

    @Override
    public void assertPage() {
        Assert.assertEquals(driver.getTitle(), AMZ_PAGE_TITLE);
    }

    @Override
    public void assertPage(String param) {

    }

    public void signIn() {
        Actions action = new Actions(driver);
        if (AmzSingInBtn.isDisplayed()) {
            action.moveToElement(AmzSingInBtn).build().perform();
            if (AmzSingInNewCtmrBtn.isDisplayed()) {
                AmzSingInNewCtmrBtn.click();
            } else {
                Assert.fail("Error: '" + AMZ_SIGN_IN_NEW_CTMR_BTN + "' button is not displayed.");
            }
        } else {
            Assert.fail("Error: '" + AMZ_SIGN_IN_BTN + "' button is not displayed.");
        }
    }


}
