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
}
