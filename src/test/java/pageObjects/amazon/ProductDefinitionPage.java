package pageObjects.amazon;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import selenium.ButtonFunctions;
import selenium.EditBoxFunctions;
import selenium.GenericObjectFunctions;

public class ProductDefinitionPage extends PageBase {

    protected EditBoxFunctions editBox = null;
    protected ButtonFunctions button = null;
    final String AMZ_PAGE_TITLE="";
    final String AMZ_PDP_PROD_PRICE = "priceblock_ourprice";
    final String AMZ_PDP_ADD_TO_CAR_BTN = "add-to-cart-button";


    @FindBy(id = AMZ_PDP_PROD_PRICE)
    WebElement AmzPdpProdPrice;

    @FindBy(id = AMZ_PDP_ADD_TO_CAR_BTN)
    WebElement AmzPdpAddToCarButton;


    public ProductDefinitionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected void setFunctions() {

    }

    @Override
    public void assertPage() {
        try {
            Assert.assertTrue(obj.waitForElementToExist(AmzPdpAddToCarButton, 20));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assertPage(String param) {

    }

    public void comparePrices(String index){
        try {
            String pdpPrice=AmzPdpProdPrice.getText().replace("\n", ".");
            String resultGridPrice=storage.get("priceValue" + index);
            Assert.assertEquals(resultGridPrice, pdpPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToCar(){
            AmzPdpAddToCarButton.click();
    }
}
