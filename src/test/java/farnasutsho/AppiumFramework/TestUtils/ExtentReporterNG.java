package farnasutsho.AppiumFramework.TestUtils;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	static ExtentReports extent ;
	
	@BeforeTest
	public static ExtentReports getReporterObject() {
		
		String path = System.getProperty("user.dir")+("\\reports\\index.html");
		
		//ExtentSparkReporter
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Dummy Test");
		reporter.config().setDocumentTitle("Dummy Test");
		
	    extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester","Farnas Utsho");
		return extent;
		

		
		
	}
	
}
