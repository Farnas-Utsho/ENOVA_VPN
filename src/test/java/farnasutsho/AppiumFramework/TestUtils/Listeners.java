package farnasutsho.AppiumFramework.TestUtils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import farnasutsho.AppiumFramework.utils.AppiumUtils;
import io.appium.java_client.AppiumDriver;


public class Listeners extends AppiumUtils implements ITestListener {

    ExtentTest test;
    static ExtentReports extent = ExtentReporterNG.getReporterObject();
    AppiumDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable());

        try {
            driver = (AppiumDriver) result.getTestClass()
                    .getRealClass()
                    .getField("driver")
                    .get(result.getInstance());

            String screenshotPath = getScreenshotPath(
                    result.getMethod().getMethodName(), driver);

            test.addScreenCaptureFromPath(screenshotPath,
                    result.getMethod().getMethodName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
