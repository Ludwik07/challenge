package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverSetup {

    private int minTimeOut = 0;
    private int maxTimeOut = 120;
    private int defaultImplicitWaitTime = 5;
    public WebDriver driver;
    private String browser;
    private String chromePath;
    private String firefoxPath;

    /**
     * Sets a Web Driver
     *
     * @param browser      supports "chrome" and "firefox" for now
     * @param driversPath  where are your drivers located?
     * @param implicitWait
     */
    public DriverSetup(String browser, String driversPath, int implicitWait) throws Exception {
        String path = driversPath.replace("/", "\\");
        this.browser = browser.toLowerCase();
        this.chromePath = System.getProperty("user.dir") + path + "chromedriver.exe";
        this.firefoxPath = System.getProperty("user.dir") + path + "geckodriver.exe";
        switch (getBrowser()) {
            case "chrome":
                try {
                    this.driver = setChromeDriver();
                }catch (Exception ex){
                    System.out.println("Error setting " + browser + " driver. Exeption: " +ex);
                    throw ex;
                }
                break;
            case "firefox":
                try {
                    this.driver = setFFDriver();
                }catch (Exception ex){
                    System.out.println("Error setting " + browser + " driver. Exeption: " +ex);
                    throw ex;
                }
                break;
            default:
                this.driver = null;
                //log,
                System.out.println("unable to set driver, " + browser + " is not a valid selection. Select chrome or firefox");
        }
        this.driver.manage().timeouts().implicitlyWait(setTimeOut(implicitWait), TimeUnit.SECONDS);
    }

    /**
     * @return browser used to set driver
     */
    public String getBrowser() {
        return this.browser;
    }

    /**
     * @return WebDriver that has been configured
     */
    public WebDriver getDriver() {
        return this.driver;
    }

    private WebDriver setChromeDriver() {
        //Set a Chrome driver
        System.setProperty("webdriver.chrome.driver", chromePath);
        return new ChromeDriver();
    }

    private WebDriver setFFDriver() {
        //Set a FF driver
        System.setProperty("webdriver.gecko.driver", firefoxPath);
        return new FirefoxDriver();
    }

    /**
     * Need to refine this one...
     *
     * @param time
     * @return
     */
    private int setTimeOut(int time) {
        if (time > minTimeOut && time < maxTimeOut) {
            return time;
        } else {
            return defaultImplicitWaitTime;
            //log here that we set default because time provided is not accepted
        }
    }


}
