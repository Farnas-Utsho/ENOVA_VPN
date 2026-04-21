package farnasutsho.AppiumFramework.TestUtils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    static ExtentReports extent;

    public static ExtentReports getReporterObject() {

        // Create reports directory inside project
        String reportPath = System.getProperty("user.dir") 
                + "/reports/index.html";

        File reportDir = new File(System.getProperty("user.dir") 
                + "/reports");

        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

        reporter.config().setReportName("Enova serve Test");
        reporter.config().setDocumentTitle("Server Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);

        extent.setSystemInfo("Tester", "Farnas Utsho");
        extent.setSystemInfo("Platform", "Android");
        extent.setSystemInfo("Framework", "Appium + TestNG");

        return extent;
    }
} 