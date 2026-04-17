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

public class Listeners  extends AppiumUtils implements ITestListener {
	AppiumDriver driver;
	ExtentTest test ;
    ExtentReports extent = ExtentReporterNG.getReporterObject();
    // Runs before any test method is executed in the suite
    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Test Suite Started: " + context.getName() + " ===");
    }

    // Runs after all test methods in the suite have been executed
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== Test Suite Finished: " + context.getName() + " ===");
        System.out.println("Total Tests: " + context.getAllTestMethods().length);
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
        extent.flush();
    }

    // Runs before each test method
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        test = extent.createTest(result.getMethod().getMethodName());
    }

    // Runs when a test method succeeds
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test PASSED: " + result.getName());
        test.log(Status.PASS,"Test Passed");
    }

    // Runs when a test method fails
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test FAILED: " + result.getName());
        System.out.println("Reason: " + result.getThrowable().getMessage());
        test.fail(result.getThrowable());
        
       try {
		driver = (AppiumDriver) result.getClass().getField("driver").get(result.getInstance());
	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       try {
		test.addScreenCaptureFromPath(getScreenShot(result.getMethod().getMethodName(),driver),null);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        
        // You can add screenshot capture logic here for Appium failures
        // takeScreenshot(result);
    }

    // Runs when a test method is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⏭️ Test SKIPPED: " + result.getName());
    }

    // Runs when a test method fails but is within the success percentage
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("⚠️ Test FAILED but within success percentage: " + result.getName());
    }

    // Runs when a test is failed due to timeout
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("⏰ Test FAILED due to Timeout: " + result.getName());
        onTestFailure(result); // You can call onTestFailure for common handling
    }
}