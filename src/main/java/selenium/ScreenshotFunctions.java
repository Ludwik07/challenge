package selenium;

import commons.GeneralUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class ScreenshotFunctions {

    GeneralUtils util = new GeneralUtils();

    public String getScreenShoot(String testCaseName, WebDriver driver) throws IOException {
        String path=null;
        TakesScreenshot ss =(TakesScreenshot)driver;
        File source=ss.getScreenshotAs(OutputType.FILE);
        String fileName=util.getCurrentTimeStamp("yyyy-MM-dd_HH-mm-ss")+".png";
        path= System.getProperty("user.dir")+"\\reports\\" + testCaseName+"-"+fileName;
        File destination=new File(path);
        FileUtils.copyFile(source, destination);
        return path;
    }


}
