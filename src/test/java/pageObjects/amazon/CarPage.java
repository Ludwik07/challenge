package pageObjects.amazon;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import selenium.ButtonFunctions;
import selenium.EditBoxFunctions;

public class CarPage extends PageBase {

    protected EditBoxFunctions editBox = null;
    protected ButtonFunctions button = null;
    final String AMZ_PAGE_TITLE = "Amazon.com Shopping Cart";
    final String AMZ_CART_BTN = "nav-cart-count";
    final String AMZ_CART_PROD_PRICE_SPAN = "//form[@id='activeCartViewForm']//p[@class='a-spacing-small']/span";
    final String AMZ_CART_PROD_DELETE_BTN = "//form[@id='activeCartViewForm']//input[@data-action='delete']";

    final String AMZ_CART = "sc-active-cart";
    final String AMZ_CART_CHK_OUT_BTN = "sc-buy-box-ptc-button";
    final String AMZ_CART_EMPTY_MSG = "//*[@id='sc-active-cart']//h2[contains(text(),'Your Amazon Cart is empty')]";


    @FindBy(xpath = AMZ_CART_EMPTY_MSG)
    WebElement AmzCartEmptyMsg;
    @FindBy(xpath = AMZ_CART_PROD_DELETE_BTN)
    WebElement AmzCartProdDelBtn;
    @FindBy(id = AMZ_CART)
    WebElement AmzCart;
    @FindBy(xpath = AMZ_CART_PROD_PRICE_SPAN)
    WebElement AmzCartProdPriceSpan;
    @FindBy(id = AMZ_CART_BTN)
    WebElement AmzCartBtn;
    @FindBy(id = AMZ_CART_CHK_OUT_BTN)
    WebElement AmzCartCheckOutBtn;


    public CarPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(super.driver, this);
        setFunctions();
    }

    @Override
    protected void setFunctions() {
        editBox = new EditBoxFunctions();
        button = new ButtonFunctions();
    }

    @Override
    public void assertPage() {
        System.out.println("Expected: '" + AMZ_PAGE_TITLE + "', Found: '" + driver.getTitle() + "'");
        Assert.assertEquals(driver.getTitle(), AMZ_PAGE_TITLE);
    }

    @Override
    public void assertPage(String param) {

    }

    public void goToCar() {
        AmzCartBtn.click();
        try {
            obj.waitForElementToExist(AmzCart, 20);
        } catch (InterruptedException e) {
            Assert.fail("Cart not displayed", e);
            e.printStackTrace();
        }
    }

    public void comparePrices(String index) {
        try {
            String pdpPrice = AmzCartProdPriceSpan.getText().replace("\n", ".");
            String resultGridPrice = storage.get("priceValue" + index);
            Assert.assertEquals(resultGridPrice, pdpPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkout() {
        AmzCartCheckOutBtn.click();
    }

    public void deleteItem() {
        AmzCartProdDelBtn.click();
        Assert.assertTrue(AmzCartEmptyMsg.isDisplayed());
    }
}
