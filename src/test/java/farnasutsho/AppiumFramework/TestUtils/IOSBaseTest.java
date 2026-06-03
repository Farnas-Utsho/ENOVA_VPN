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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class IOSBaseTest  extends AppiumUtils{

	public IOSDriver driver;
	public AppiumDriverLocalService service ;
	
	 private String runCommand(String command) throws IOException {
	        String[] env = {
	            "PATH=/usr/local/bin:/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin"
	        };
	        Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command}, env);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        StringBuilder output = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            output.append(line.trim());
	        }
	        return output.toString().trim();
	    }


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

	// ✅ AUTO-DETECT: Get UDID of the first connected iOS device
	String udid = runCommand("idevice_id -l | head -1");
	if (udid == null || udid.isEmpty()) {
	    throw new RuntimeException("❌ No iOS device detected. Please connect a device via USB.");
	}
	System.out.println("✅ Auto-detected UDID: " + udid);

	// ✅ AUTO-DETECT: Get device name using ideviceinfo
	String deviceName = runCommand("ideviceinfo -u " + udid + " -k DeviceName");
	if (deviceName == null || deviceName.isEmpty()) deviceName = "iOS Device";
	System.out.println("✅ Auto-detected Device Name: " + deviceName);

	// ✅ AUTO-DETECT: Get iOS platform version using ideviceinfo
	String platformVersion = runCommand("ideviceinfo -u " + udid + " -k ProductVersion");
	if (platformVersion == null || platformVersion.isEmpty()) platformVersion = "17.0";
	System.out.println("✅ Auto-detected Platform Version: " + platformVersion);

	options.setDeviceName(deviceName);           // ✅ was: "iPad"
	options.setUdid(udid);                       // ✅ was: "00008101-000339611A79A01E"
	options.setPlatformVersion(platformVersion); // ✅ was: "26.3.1"

	// ✅ UNCHANGED: Code signing (MANDATORY for real device)
	options.setCapability("xcodeOrgId", "37Q2WF67T5");
	options.setCapability("xcodeSigningId", "iPhone Developer");

	// ✅ ADDED: WDA + stability configs
	options.setUseNewWDA(true);
	options.setNoReset(true);
	options.setShowXcodeLog(true);

	// ❌ REMOVED .app path (real device doesn't need it if app already installed)
	// options.setApp("...");

	// ✅ ADDED: Use bundleId instead
	 options.setBundleId("com.enovavpn.mobile");

	options.setWdaLaunchTimeout(Duration.ofSeconds(100));

	driver = new IOSDriver(service.getUrl(), options);

	}

	@AfterClass
	public void tearDown() {

	driver.quit();
	service.close();
	}
}