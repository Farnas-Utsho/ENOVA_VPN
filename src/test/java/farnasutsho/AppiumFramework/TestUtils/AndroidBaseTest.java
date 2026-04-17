package farnasutsho.AppiumFramework.TestUtils;

import org.testng.annotations.BeforeClass;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;
import org.testng.annotations.AfterClass;

import farnasutsho.AppiumFramework.utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


public class AndroidBaseTest extends AppiumUtils {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void ConfigureAppium() throws URISyntaxException, IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "//src//main//java//farnasutsho//AppiumFramework//resources//data.properties");
        prop.load(fis);

        String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");

        // Start Appium server programmatically
        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setUdid("3510232000002415"); 

        // App info
        options.setAppPackage("com.enovavpn.mobile");
        options.setAppActivity("com.enovavpn.mobile.MainActivity");

        // Wait for the app's main activity
        options.setAppWaitActivity("com.enovavpn.mobile.MainActivity");
        options.setAppWaitDuration(Duration.ofSeconds(60)); // Wait up to 60s for app to load

        // Avoid reinstalling every time
        options.setNoReset(true);
        options.setFullReset(false);

        // Initialize driver
        driver = new AndroidDriver(service.getUrl(), options);

        // Implicit wait as a safety net
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

   
	@AfterClass
    public void tearDown() {
        if(driver != null) driver.quit();
        if(service != null) service.close();
    }
}