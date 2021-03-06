package common;

import api.APIFunctions;
import base.IScriptsBase;
import commons.PropertiesFunctions;
import jsonFunctions.JsonFunctions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.amazon.*;
import selenium.DriverSetup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class AmazonScripts implements IScriptsBase {

    final private String DRIVERS_PATH = "/src/main/resources/drivers/"; //should start and end with "/"
    final private String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/java/resources/amazon/";
    protected APIFunctions api = new APIFunctions();

    protected WebDriver driver;

    protected LandingPage landing;
    protected SearchResultPage results;
    protected ProductDefinitionPage pdp;
    protected CarPage cart;
    protected CheckOutPage checkOut;
    protected RegistrationPage registration;
    protected HelpPage help;


    /**
     * Launches a browser as per parameter (chrome or firefox) and sets implicit wait
     *
     * @param browser chrome or ff
     * @return webdriver id
     */
    public void launchBrowser(String browser, int implicitWait) {
        try {
            DriverSetup setup = new DriverSetup(browser, DRIVERS_PATH, implicitWait);
            this.driver = setup.getDriver();
            driver.manage().window().maximize();
        } catch (Exception ex) {
            //A log approach here would be more suitable, meanwhile..
            System.out.println("Error while loading browser" + browser + ". Exception: " + ex);
        }
    }

    /**
     * Sets Page objects for landing page
     */
    public void setRegistrationPage() {
        try {
            registration = new RegistrationPage(this.driver);
        } catch (Exception ex) {
            //A log approach here would be more suitable, meanwhile..
            System.out.println("Error while loading object landing page. Exception: " + ex);
        }
    }

    /**
     * Sets Page objects for landing page
     */
    public void setLandingPage() {
        try {
            landing = new LandingPage(this.driver);
        } catch (Exception ex) {
            //A log approach here would be more suitable, meanwhile..
            System.out.println("Error while loading object landing page. Exception: " + ex);
        }
    }

    /**
     * Sets Page objects for landing page
     */
    public void setHelpPage() {
        try {
            help = new HelpPage(this.driver);
        } catch (Exception ex) {
            //A log approach here would be more suitable, meanwhile..
            System.out.println("Error while loading object landing page. Exception: " + ex);
        }
    }

    /**
     * Sets Page objects for search results page
     */
    public void setSearchResultsPage() {
        try {
            results = new SearchResultPage(this.driver);
        } catch (Exception ex) {
            //A log approach here would be more suitable, meanwhile..
            System.out.println("Error while loading object landing page. Exception: " + ex);
        }
    }

    /**
     * Sets Page objects for pdp page
     */
    public void setPDPage() {
        try {
            pdp = new ProductDefinitionPage(this.driver);
        } catch (Exception ex) {
            //A log approach here would be more suitable, meanwhile..
            System.out.println("Error while loading object landing page. Exception: " + ex);
        }
    }

    /**
     * Sets Page objects for checkout page
     */
    public void setCheckOutPage() {
        try {
            checkOut = new CheckOutPage(this.driver);
        } catch (Exception ex) {
            //A log approach here would be more suitable, meanwhile..
            System.out.println("Error while loading object landing page. Exception: " + ex);
        }
    }

    /**
     * Sets Page objects for checkout page
     */
    public void setCarPage() {
        try {
            cart = new CarPage(this.driver);
        } catch (Exception ex) {
            //A log approach here would be more suitable, meanwhile..
            System.out.println("Error while loading object landing page. Exception: " + ex);
        }
    }

    /**
     * navigates to a given url
     *
     * @param url
     */
    public void navigate(String url) {
        try {
            if (!url.isEmpty()) {
                driver.get(url);
            } else {
                throw new Exception("Cannot navigate empty url");
            }
        } catch (Exception ex) {
            //A log approach here would be more suitable, meanwhile..
            System.out.println("Error while loading url: " + url + ". Exception: " + ex);
        }
    }

    /**
     * @param jsonFile json file name
     * @return Object array, Retrieves json as table
     */
    public Object[][] getJsonTestData(String jsonFile) {
        try {
            JsonFunctions json = new JsonFunctions();
            json.readFromFile(RESOURCES_PATH + jsonFile);
            return json.getTableFromJArray();
        } catch (Exception e) {
            System.out.println("Could not read json " + jsonFile + " Error:" + e);
        }
        return null;
    }

    /**
     * @param propertyFile property file name
     * @param property     property name
     * @return property value as String
     */
    public String getProperty(String propertyFile, String property) {
        PropertiesFunctions prop = new PropertiesFunctions();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(RESOURCES_PATH + propertyFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return prop.getProperty(fis, property);
    }

    public String getApiInfo(String url, String jPath) {
        JsonFunctions json = new JsonFunctions();
        String response=null;
        try {
            response = api.get(url, 200);
            System.out.println("This is the api response:" + response);
        } catch (Exception e) {
            Assert.fail("Unable to read from API '" + url + "', Exception: ", e);
            e.printStackTrace();
        }
        if (response.isEmpty()) {
            Assert.fail("API response is empty");
        }
        return json.getPathKey(response, jPath);
    }

}
