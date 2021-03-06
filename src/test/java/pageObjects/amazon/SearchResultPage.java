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


public class SearchResultPage extends PageBase {

    protected EditBoxFunctions editBox = null;
    protected ButtonFunctions button = null;
    final String AMZ_RESULTS_SEARCH_RES_0 = "//*[@data-cel-widget='search_result_0']";
    final String AMZ_RESULTS_SEARCH_RES_1 = "//*[@data-cel-widget='search_result_0']";
    final String AMZ_RESULTS_SEARCH_RES_X_LINK = "//*[@data-cel-widget='search_result_${{resultIndex}}']//span[@class='a-size-medium a-color-base a-text-normal']"; //where ${{ }} are boundaries for replace a value
    final String AMZ_RESULTS_SEARCH_RES_X_PRICE = "//*[@data-cel-widget='search_result_${{resultPriceIndex}}']//span[@class='a-price']"; //where ${{ }} are boundaries for replace a value
    final String AMZ_RESULTS_SEARCH_RES_X_IMG = "//*[@data-cel-widget='search_result_${{resultImgIndex}}']//img[@class='s-image']";

    @FindBy(xpath = AMZ_RESULTS_SEARCH_RES_0)
    WebElement AmzResultsSearchRes0;
    @FindBy(xpath = AMZ_RESULTS_SEARCH_RES_1)
    WebElement AmzResultsSearchRes1;

    @Override
    public void setFunctions() {
        try {
            editBox = new EditBoxFunctions();
            button = new ButtonFunctions();
        } catch (Exception ex) {
            System.out.println("Error while setting test: " + ex);
        }
    }

    @Override
    public void assertPage() {

    }

    public SearchResultPage(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void validateResult(String value) {
        try {
            if (!obj.waitForElementToExist(AmzResultsSearchRes0, 15)) {
                Assert.fail("Error: element '" + AMZ_RESULTS_SEARCH_RES_0 + "' does not exist.");
            }
            if (!AmzResultsSearchRes0.getText().contains(value)) {
                Assert.fail("Error: Text: '" + value + "' not found in: " +AmzResultsSearchRes0.getText());
            }
        } catch (Exception ex) {
            System.out.println("Error while setting test: " + ex);
        }
    }

    public void openResult(String index) {
        String locator = null;
        try {
            storage.add("resultImgIndex", index);
            locator = storage.replaceVar(AMZ_RESULTS_SEARCH_RES_X_IMG);
            obj.waitForElementToExist(locator, 20);
            WebElement element = obj.getElement(locator);
            element.click();
        } catch (Exception ex) {
            Assert.fail("Error: Unable to click element '" + locator + "'. Exception: " + ex);
        }
    }

    public void storePrice(String index) {
        String locator = null;
        try {
            storage.add("resultPriceIndex", index);
            locator = storage.replaceVar(AMZ_RESULTS_SEARCH_RES_X_PRICE);
            obj.waitForElementToExist(locator, 20);
            WebElement element = obj.getElement(locator);
            String price = element.getText().replace("\n", ".");
            ;
            storage.add("priceValue" + index, price);
        } catch (Exception ex) {
            Assert.fail("Error: Unable to find element '" + locator + "'");
        }
    }

    @Override
    public void assertPage(String value) {
        Assert.assertEquals(super.driver.getTitle(), value);
    }
}
