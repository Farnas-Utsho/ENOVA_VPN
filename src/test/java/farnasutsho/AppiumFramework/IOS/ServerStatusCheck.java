package farnasutsho.AppiumFramework.IOS;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import farnasutsho.AppiumFramework.TestUtils.IOSBaseTest;

import farnasutsho.AppiumFramework.iOS.IOSHomePage;
import farnasutsho.AppiumFramework.iOS.IOSLocationPage;
import farnasutsho.AppiumFramework.iOS.IOSThirdPartyAPP;
import io.appium.java_client.AppiumDriver;

public class ServerStatusCheck extends IOSBaseTest{
	
	
	public AppiumDriver drive;
	
	public IOSHomePage home;
	public IOSLocationPage location;
	
	public IOSThirdPartyAPP app;
	
	
	
	@AfterMethod	
	public void preSetup() {
		driver.activateApp("com.enovavpn.mobile");
	
		
	}
	
	@Test(dataProvider="getData") 
	public void serverTest(HashMap<String ,String> input) throws InterruptedException {
		
		home = new IOSHomePage(driver);
		location = new IOSLocationPage (driver);
		app = new IOSThirdPartyAPP(driver);
		
		home.goToLocationPage();
		String country = input.get("country"); 
		String server = input.get("Server"); 
		
		if (country == null || country.trim().isEmpty()) {
		    location.SelectServer(server);
		} else {
		    location.SelectCountry(country);
		    location.SelectServer(server);
		}
		home.clickconnect(); 
		Thread.sleep(8000);
		
		driver.activateApp("com.monvpn.myip");
		Thread.sleep(8000);
		
		String actualIP= app.extractIP(); 
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
	    		+ "/src/test/java/farnasutsho/AppiumFramework/testData/serverlist.json"
	    );

	    Object[][] arr = new Object[data.size()][1]; // 1 parameter per row

	    for (int i = 0; i < data.size(); i++) {
	        arr[i][0] = data.get(i); // each HashMap is a separate test row
	    }

	    return arr;
	}
	
	

}
