package pageObjects.amazon;

import base.PageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import selenium.EditBoxFunctions;

public class HelpPage extends PageBase {

    protected EditBoxFunctions editBox = null;

    final String AMZ_PAGE_TITLE = "Amazon.com Help: Conditions of Use";
    final String AMZ_HELP_ALL_TOPICS_LINK = "//a[.='All Help Topics']";
    final String AMZ_HELP_SEARCH_RES_ECHO_SUPPORT_LINK = "//div[@class='cs-search-result-wrapper']//a[contains(text(),'Echo Support')]";
    final String AMZ_HELP_SEARCH_TEXTBOX = "helpsearch";
    final String AMZ_HELP_SEARCH_RES_ECHO_SUPPORT_GETTING_STARTED_H4 = "//h4[.='Getting Started']";
    final String AMZ_HELP_SEARCH_RES_ECHO_SUPPORT_WIFI_AND_BT_H4 = "//h4[.='Wi-Fi and Bluetooth']";
    final String AMZ_HELP_SEARCH_RES_ECHO_SUPPORT_DEV_AND_HW_H4 = "//h4[.='Device Software and Hardware']";
    final String AMZ_HELP_SEARCH_RES_ECHO_SUPPORT_TROUBLESHOOTING_H4 = "//h4[.='Troubleshooting']";

    @FindBy(xpath = AMZ_HELP_SEARCH_RES_ECHO_SUPPORT_GETTING_STARTED_H4)
    WebElement amzHelpSerchResEchoSupportGettingStarted_h4;
    @FindBy(xpath = AMZ_HELP_SEARCH_RES_ECHO_SUPPORT_WIFI_AND_BT_H4)
    WebElement amzHelpSerchResEchoSupportWifiAndBT_h4;
    @FindBy(xpath = AMZ_HELP_SEARCH_RES_ECHO_SUPPORT_DEV_AND_HW_H4)
    WebElement amzHelpSerchResEchoSupportDenAndHW_h4;
    @FindBy(xpath = AMZ_HELP_SEARCH_RES_ECHO_SUPPORT_TROUBLESHOOTING_H4)
    WebElement amzHelpSerchResEchoSupportTroubleshooting_h4;

    @FindBy(xpath = AMZ_HELP_SEARCH_RES_ECHO_SUPPORT_LINK)
    WebElement amzHelpSerchResEchoSupportLink;

    @FindBy(id = AMZ_HELP_SEARCH_TEXTBOX)
    WebElement amzHelpSerchTextbox;

    @FindBy(xpath = AMZ_HELP_ALL_TOPICS_LINK)
    WebElement amzHelpAllTopicsLink;


    @Override
    public void setFunctions() {
        try {
            editBox = new EditBoxFunctions();
        } catch (Exception ex) {
            System.out.println("Error while setting test: " + ex);
        }
    }

    public HelpPage(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(super.driver, this);
        setFunctions();
    }

    @Override
    public void assertPage() {
        Assert.assertEquals(driver.getTitle(), AMZ_PAGE_TITLE);
    }

    @Override
    public void assertPage(String param) {

    }


    public void navigateSearch(String search) {
        try {
            obj.waitForElementToExist(amzHelpAllTopicsLink, 10);
        } catch (Exception e) {
            Assert.fail("Error: '" + AMZ_HELP_ALL_TOPICS_LINK + "' link is not displayed.");
        }
        amzHelpAllTopicsLink.click();
        try {
            editBox.type(amzHelpSerchTextbox, search);
            amzHelpSerchTextbox.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            Assert.fail("Error: '" + AMZ_HELP_SEARCH_TEXTBOX + "' textbox is not displayed.");
        }
        try {
            obj.waitForElementToExist(amzHelpSerchResEchoSupportLink, 10);
        } catch (Exception e) {
            Assert.fail("Error: '" + AMZ_HELP_SEARCH_RES_ECHO_SUPPORT_LINK + "' link is not displayed.");
        }
        amzHelpSerchResEchoSupportLink.click();
    }

    public void assertHelpResultContent() {
        Assert.assertTrue(amzHelpSerchResEchoSupportGettingStarted_h4.isDisplayed());
        Assert.assertTrue(amzHelpSerchResEchoSupportWifiAndBT_h4.isDisplayed());
        Assert.assertTrue(amzHelpSerchResEchoSupportDenAndHW_h4.isDisplayed());
        Assert.assertTrue(amzHelpSerchResEchoSupportTroubleshooting_h4.isDisplayed());
    }

}
