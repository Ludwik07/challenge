package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import selenium.ScreenshotFunctions;

import java.io.IOException;

public class Listeners extends ScreenshotFunctions implements ITestListener {

    ExtentReports extent = ExtentReporter.getReportObject();
    ExtentTest test = null;
    ThreadLocal <ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getTestClass().getRealClass().getName()+" - "+ result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        extentTest.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
            extentTest.get().addScreenCaptureFromPath(getScreenShoot(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
        } catch (IllegalAccessException | IOException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
