package farnasutsho.AppiumFramework.TestUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import farnasutsho.AppiumFramework.utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBaseTest extends AppiumUtils{
<<<<<<< HEAD
	public IOSDriver driver;
	public AppiumDriverLocalService service ;
	
	
			@BeforeClass
			public void ConfigureAppium() throws URISyntaxException, IOException {
			//AndroidDriver , IOSDriver
			
				
				Properties prop = new Properties();	
				
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//farnasutsho//AppiumFramework//resources//data.properties");
				
				prop.load(fis);
				
				String ipAddress = prop.getProperty("ipAddress");
				String port = prop.getProperty("port");
				
				
				//Start appium server programmatically
			
			XCUITestOptions options = new XCUITestOptions();
			
			options.setDeviceName("iPhone 17 Pro Max");
			//options.setApp("//Users//farnasutsho//eclipse-workspace//AppiumFramework//src//test//java//resources//ApiDemos-debug.apk");
			
			options.setApp("//Users//farnasutsho//eclipse-workspace//AppiumFramework//src//test//java//resources//UIKitCatalog.app");
			options.setPlatformVersion("26.4");
			options.setWdaLaunchTimeout(Duration.ofSeconds(20));
			
			driver = new IOSDriver(service.getUrl(), options);
			
			
		}
			
		
		@AfterClass
		public void tearDown() {
			
			driver.quit();
			service.close();
		}
=======
public IOSDriver driver;
public AppiumDriverLocalService service ;

@BeforeClass
public void ConfigureAppium() throws URISyntaxException, IOException {

//AndroidDriver , IOSDriver

Properties prop = new Properties();

FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//farnasutsho//AppiumFramework//resources//data.properties");

prop.load(fis);

String ipAddress = prop.getProperty("ipAddress");
String port = prop.getProperty("port");

//Start appium server programmatically
service = startAppiumServer(ipAddress, Integer.parseInt(port));
XCUITestOptions options = new XCUITestOptions();

// ✅ CHANGED: Real device name
options.setDeviceName("iPad");

// ✅ ADDED: Required for real device
options.setUdid("00008101-000339611A79A01E");

// ✅ CHANGED: Platform version
options.setPlatformVersion("26.3.1");

// ✅ ADDED: Code signing (MANDATORY for real device)
options.setCapability("xcodeOrgId", "37Q2WF67T5");
options.setCapability("xcodeSigningId", "iPhone Developer");

// ✅ ADDED: WDA + stability configs
options.setUseNewWDA(true);
options.setNoReset(true);
options.setShowXcodeLog(true);

// ❌ REMOVED .app path (real device doesn't need it if app already installed)
// options.setApp("...");

// ✅ ADDED: Use bundleId instead
options.setBundleId("tech.nagorik.sharkvpn");

options.setWdaLaunchTimeout(Duration.ofSeconds(100));

driver = new IOSDriver(service.getUrl(), options);

>>>>>>> 05a332220c95ee5e4e787c0fc8b7e3665884913b
}

@AfterClass
public void tearDown() {

driver.quit();
service.close();
}
}