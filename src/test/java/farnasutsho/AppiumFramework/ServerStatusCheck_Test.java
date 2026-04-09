package farnasutsho.AppiumFramework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import farnasutsho.AppiumFramework.TestUtils.AndroidBaseTest;
import farnasutsho.AppiumFramework.android.HomePage;

import farnasutsho.AppiumFramework.android.LocationPage;
import io.appium.java_client.AppiumDriver;



public class ServerStatusCheck_Test extends AndroidBaseTest{
	
	public AppiumDriver drive;
	
	
	public HomePage home;
	
	public LocationPage location;
	
	
//	
	
	@AfterMethod
	public void preSetup() throws InterruptedException {
		driver.terminateApp("com.enovavpn.mobile");
	     
		Thread.sleep(1000);
	     
	     driver.activateApp("com.enovavpn.mobile"); 
	   
	}
	
	
	
	
	@Test(dataProvider="getData")
	public void serverTest(HashMap<String ,String> input) throws InterruptedException {

		home = new HomePage(driver);
	    location = new LocationPage(driver); 

	    String country = input.get("country");
	    String server = input.get("Server");

	    System.out.println("Testing country: " + country + ", Server: " + server);

	    // Go to server list
	    home.GoToServerList();

	    // If country is not null or empty, select it
	    if (country != null && !country.isEmpty()) {
	        location.SelectCountry(country);
	    }

	    // If server is not null or empty, select it
	    
	    location.SelectServer(server);
	   

	    // Connect and disconnect
	    Thread.sleep(2000); // wait a bit before connecting
	    home.clickConnect();
	    Thread.sleep(5000); // wait for connection to establish
	    home.clickDisConnect();

	    // Handle popups if any
	    home.ClickDisconnectOnPopUp();
	    home.connectionReportPopClose();
	    
	    
	    
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
	    List<HashMap<String, String>> data = getJsonData(
	        System.getProperty("user.dir") + "/src/test/java/farnasutsho/AppiumFramework/testData/serverlist.json"
	    );

	    Object[][] arr = new Object[data.size()][1]; // 1 parameter per row

	    for (int i = 0; i < data.size(); i++) {
	        arr[i][0] = data.get(i); // each HashMap is a separate test row
	    }

	    return arr;
	}

	
	

	
	

}
