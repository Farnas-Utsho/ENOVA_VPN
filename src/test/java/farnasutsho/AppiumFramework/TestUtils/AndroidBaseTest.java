package farnasutsho.AppiumFramework.TestUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import farnasutsho.AppiumFramework.utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest extends AppiumUtils{
	public AndroidDriver driver;
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
			  service = startAppiumServer(ipAddress,Integer.parseInt(port));
			
			//Appium code goes to appium server to interact with the device 
			//Need to server information as the argument
			//Sending details to the app
			
			UiAutomator2Options options = new UiAutomator2Options();
			
			options.setDeviceName("Pixel 4");
			//options.setApp("//Users//farnasutsho//eclipse-workspace//AppiumFramework//src//test//java//resources//ApiDemos-debug.apk");
			
			options.setAppPackage("com.enovavpn.mobile");
			options.setAppActivity("com.enovavpn.mobile.MainActivity");
			
			// Highly recommended for already installed apps
			options.setNoReset(true);
			options.setFullReset(false);
			
			driver = new AndroidDriver(service.getUrl(), options);
			
			
		}
			
			
			//From this section  all the common code snippet should be enlisted so that those could be reuse again and again 
			
			
		
		@AfterClass
		public void tearDown() {
			
			driver.quit();
			service.close();
		}
}
