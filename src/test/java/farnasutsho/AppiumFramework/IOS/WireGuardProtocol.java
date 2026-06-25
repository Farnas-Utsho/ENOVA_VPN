package farnasutsho.AppiumFramework.IOS;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import farnasutsho.AppiumFramework.TestUtils.IOSBaseTest;

import farnasutsho.AppiumFramework.iOS.IOSHomePage;
import farnasutsho.AppiumFramework.iOS.IOSLocationPage;
import farnasutsho.AppiumFramework.iOS.IOSThirdPartyAPP;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class WireGuardProtocol extends IOSBaseTest{
	
	public AppiumDriver drive;
	
	public IOSHomePage home;
	public IOSLocationPage location;
	
	public IOSThirdPartyAPP app;
	
	
	@BeforeClass
	public void setupSafari() {
	    driver.activateApp("com.apple.mobilesafari");
	    driver.get("https://api.ipify.org");
	}

	
	@BeforeMethod
	public void Setup() throws InterruptedException {
	    // Terminate the app completely, then relaunch fresh
	    driver.terminateApp("com.enovavpn.mobile");
	    driver.activateApp("com.enovavpn.mobile");

	    // Wait for app to fully load before test begins
	    Thread.sleep(3000); // or use explicit wait on home screen element
	}
	
	@AfterMethod
	public void preSetup(ITestResult result) {
	    try {
	        // If test failed, try to navigate back or force terminate
	        if (result.getStatus() == ITestResult.FAILURE) {
	            driver.terminateApp("com.enovavpn.mobile");
	        }
	    } catch (Exception e) {
	        System.out.println("AfterMethod cleanup error: " + e.getMessage());
	        driver.terminateApp("com.enovavpn.mobile"); // force kill anyway
	    }
	}
	
	@Test(dataProvider="getData") 
	public void serverTest(HashMap<String ,String> input) throws InterruptedException {

	 

	    home = new IOSHomePage(driver);
	    location = new IOSLocationPage(driver);
	    app = new IOSThirdPartyAPP(driver);

	    home.goToLocationPage();

	    String country = input.get("country"); 
	    String server = input.get("Server"); 
	    try {
	        if (country == null || country.trim().isEmpty()) {
	            location.SelectServer(server);
	        } else {
	            location.SelectCountry(country);
	            location.SelectServer(server);
	        }
	    } catch (Exception e) {
	        System.out.println("Server not found: " + server + " | " + e.getMessage());
	        throw new SkipException("Skipping test — server not found: " + server);
	    }


	  
	    home.clickconnect(); 
        Thread.sleep(8000);

	    Thread.sleep(8000);
	    driver.activateApp("com.apple.mobilesafari");

	    String actualIP = app.extractIP(); 
	    System.out.println("Actual IP From : "+actualIP);

	    driver.activateApp("com.enovavpn.mobile");
	    Thread.sleep(8000);
	    
	    home.clickDisconnect();
        home.clickdisconnectOnPopup();

	    String enovaIP = home.extractIP();
        home.clickCloseConnectionReport();

	    Assert.assertEquals(enovaIP, actualIP);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
	    List<HashMap<String, String>> data = getJsonData(
	    		System.getProperty("user.dir")
	    		+ "/src/test/java/farnasutsho/AppiumFramework/testData/serverlist_wireguard.json"
	    );

	    Object[][] arr = new Object[data.size()][1]; // 1 parameter per row

	    for (int i = 0; i < data.size(); i++) {
	        arr[i][0] = data.get(i); // each HashMap is a separate test row
	    }

	    return arr;
	}
	

}
