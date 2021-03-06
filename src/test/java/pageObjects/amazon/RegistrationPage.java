package pageObjects.amazon;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import selenium.EditBoxFunctions;

public class RegistrationPage extends PageBase {

    protected EditBoxFunctions editBox = null;

    final String AMZ_PAGE_TITLE = "Amazon Registration";
    final String AMZ_REG_CTMR_NAME_TEXTBOX = "ap_customer_name";
    final String AMZ_REG_CTMR_EMAIL_TEXTBOX = "ap_email";
    final String AMZ_REG_CTMR_PASS_TEXTBOX = "ap_password";
    final String AMZ_REG_CTMR_PASS_CHK_TEXTBOX = "ap_password_check";
    final String AMZ_REG_CONDITION_USE_LINK = "//a[.='Conditions of Use']";

    @FindBy(xpath = AMZ_REG_CONDITION_USE_LINK)
    WebElement amzRegConditionOfUseLink;
    @FindBy(id = AMZ_REG_CTMR_NAME_TEXTBOX)
    WebElement amzRegCtmrNameTextBox;
    @FindBy(id = AMZ_REG_CTMR_EMAIL_TEXTBOX)
    WebElement amzRegCtmrEmailTextBox;
    @FindBy(id = AMZ_REG_CTMR_PASS_TEXTBOX)
    WebElement amzRegCtmrPassTextBox;
    @FindBy(id = AMZ_REG_CTMR_PASS_CHK_TEXTBOX)
    WebElement amzRegCtmrPassCheckTextBox;


    @Override
    public void setFunctions() {
        try {
            editBox = new EditBoxFunctions();
        } catch (Exception ex) {
            System.out.println("Error while setting test: " + ex);
        }
    }

    public RegistrationPage(WebDriver driver) throws Exception {
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

    public void setNewCustomer(String apiInfo) {
        String [] name= apiInfo.split(" ");
        String mail= name[0]+"."+name[1]+"@fake.com";
        try {
            editBox.type(amzRegCtmrNameTextBox, apiInfo);
        } catch (Exception e) {
            Assert.fail("Failed to type into:" + AMZ_REG_CTMR_NAME_TEXTBOX);
        }
        try {
            editBox.type(amzRegCtmrEmailTextBox, mail);
        } catch (Exception e) {
            Assert.fail("Failed to type into:" + AMZ_REG_CTMR_EMAIL_TEXTBOX);
        }
    }

    public void conditionOfUse(){
        if (amzRegConditionOfUseLink.isDisplayed()) {
                amzRegConditionOfUseLink.click();
        } else {
            Assert.fail("Error: '" + AMZ_REG_CONDITION_USE_LINK + "' link is not displayed.");
        }
    }
}
