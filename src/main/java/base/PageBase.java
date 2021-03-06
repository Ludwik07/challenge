package base;

import commons.StorageFunctions;
import org.openqa.selenium.WebDriver;
import selenium.EditBoxFunctions;
import selenium.GenericObjectFunctions;

public abstract class PageBase {

    protected WebDriver driver;
    protected GenericObjectFunctions obj=null;
    public static StorageFunctions storage = new StorageFunctions();


    /** constructor
     * @param driver
     */
    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.obj = new GenericObjectFunctions(driver);
    }


    /**
     * Set ups selenium functions
     */
    protected abstract void setFunctions();
    public abstract void assertPage();
    public abstract void assertPage(String param);

    public void back (){
        driver.navigate().back();
    }

}
