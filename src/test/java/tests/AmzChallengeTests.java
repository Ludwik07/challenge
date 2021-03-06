package tests;

import org.testng.annotations.*;
import common.AmazonScripts;


public class AmzChallengeTests extends AmazonScripts {

    String url;
    String browser;
    String apiEndpoint;
    String jPath="$.data.employee_name";

    @BeforeSuite
    public void setEnv() {
        this.url = getProperty("appConf.properties", "url");
        System.out.println("Setting url: " + this.url);
        this.browser = getProperty("appConf.properties", "browser");
        System.out.println("Setting browser: " + this.browser);
        this.apiEndpoint = getProperty("appConf.properties", "apiEndpoint");
        System.out.println("Setting endpoint: " + this.apiEndpoint);

    }

    @DataProvider
    public Object[][] jsonProvider() {
        return getJsonTestData("AmzTest001.json");
    }


    /**
     * @param searchEntry keyword to be searched in the amazon search box
     * @param resultCheck index of result product to be asserted
     * @param resultsPageTitle title assertion for result page
     */

    @Test(dataProvider = "jsonProvider", enabled=true, priority=1)
    public void amzTest001(String searchEntry, String resultCheck, String resultsPageTitle) {
        launchBrowser(browser, 5);
        navigate(url); //Go to Amazon.com
        setLandingPage();
        landing.assertPage();
        landing.search(searchEntry); //Search for device as per data provider
        setSearchResultsPage();
        results.assertPage(resultsPageTitle);
        results.storePrice(resultCheck); //Verify Item is displayed on the screen and select locate the first one, then store the price
        results.openResult(resultCheck); //Click on the First Result
        setPDPage();
        pdp.comparePrices(resultCheck); //Once in the details page compare this price vs the above one
        pdp.addToCar(); //Click on Add to Cart.
        setCarPage();
        cart.assertPage();
        cart.goToCar(); //Go to Cart
        cart.comparePrices(resultCheck); // and verify again the price of the phone
        cart.checkout(); //Click on "Proceed to checkout"
        setCheckOutPage();
        checkOut.back(); //Note: no available account so getting back to delete item
        cart.deleteItem(); //Delete Item
    }

    @Test(enabled=true, priority=2)
    public void amzTest002() {
        launchBrowser(browser, 5);
        navigate(url); //Go to Amazon.com
        setLandingPage();
        landing.assertPage();
        landing.signIn(); //Locate at the upper right corner the button: Hello, Sign In Account & lists and click on it & Click on "New customer? Start right here"
        setRegistrationPage();
        registration.assertPage();
        //Fill Name field with the response of this API => [Options in the AC]
        //Fill Email field with the data from the API response Firstname.Lastname@fake.com
        registration.setNewCustomer(getApiInfo(apiEndpoint, jPath));
        //Click on Condition of Use link
        registration.conditionOfUse();
        //Locate the search bar and Search for Echo
        setHelpPage();
        help.assertPage();
        //Locate "Echo support" options and click on it
        help.navigateSearch("Echo Support");
        //Following elements should be displayed: Getting Started, Wi-Fi and Bluetooth, Device Software and Hardware, TroubleShooting*/
        help.assertHelpResultContent();
    }


    @AfterMethod
    public void terminateDriver(){
        super.driver.close();
    }


}
