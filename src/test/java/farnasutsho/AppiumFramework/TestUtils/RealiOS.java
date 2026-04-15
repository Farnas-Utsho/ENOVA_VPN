package farnasutsho.AppiumFramework.TestUtils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import farnasutsho.AppiumFramework.utils.AppiumUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class RealiOS  extends AppiumUtils{

    public IOSDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void configureAppium() throws URISyntaxException, IOException {

        // ------------------------------
        // 1. Start Appium server programmatically
        // ------------------------------
		
		Properties prop = new Properties();	
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//farnasutsho//AppiumFramework//resources//data.properties");
		
		prop.load(fis);
		
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		
		
		//Start appium server programmatically

        // ------------------------------
        // 2. Setup iOS device capabilities
        // ------------------------------
        XCUITestOptions options = new XCUITestOptions();

        options.setDeviceName("iPhone 12"); // Exact device name
        options.setPlatformVersion("16.3"); // Real device iOS version
        options.setAutomationName("XCUITest");

        // Enova VPN app
        options.setBundleId("com.enovavpn.mobile");

        // Real device UDID
        options.setUdid("00008101-000E692C1EC0001E");

        // Xcode signing details (mandatory for real devices)
        

        // WDA options
        options.setWdaLaunchTimeout(Duration.ofSeconds(120));
        options.setUseNewWDA(true);

        // ------------------------------
        // 3. Launch the app
        // ------------------------------
        driver = new IOSDriver(service.getUrl(), options);
    }

    
	@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.close();
        }
    }
}