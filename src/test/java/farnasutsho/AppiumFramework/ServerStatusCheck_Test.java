package farnasutsho.AppiumFramework;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import farnasutsho.AppiumFramework.TestUtils.AndroidBaseTest;
import farnasutsho.AppiumFramework.android.HomePage;
import farnasutsho.AppiumFramework.android.LocationPage;
import farnasutsho.AppiumFramework.android.myIPappPage;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;


public class ServerStatusCheck_Test extends AndroidBaseTest{
	
	public AppiumDriver drive;
	
	public HomePage home;
	
	public LocationPage location;
	
	public myIPappPage iptest;
	
	

	
	
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
	    iptest = new myIPappPage(driver);
	    
	    

	    String country = input.get("country");
	    String server = input.get("Server");
	    
	    
	 

	    System.out.println("Testing country: " + country + ", Server: " + server);

	   

	    // Go to server list
	    home.GoToServerList();

	    // If country is not null or empty, select it
	    
	 // Only select country if it is provided in JSON
	    if (country != null && !country.trim().isEmpty()) {
	        System.out.println("Selecting country: " + country);
	        location.SelectCountry(country);
	    } else {
	        System.out.println("No country provided, skipping country selection");
	    }
	    

	    // If server is not null or empty, select it
	    
	    location.SelectServer(server);
	   

	    // Connect and disconnect
	    // wait a bit before connecting
	    home.clickConnect();
	    
	   String expected_ip = home.VPNiPAddress();
	   
	   
	   System.out.println("IP from vpn applicatoin : "+expected_ip);
	    
	
	  
	    
	    //Collect IP from third Party Application
	    driver.activateApp("cz.webprovider.whatismyipaddress");
	    Thread.sleep(5000);
	  
	    
	    
	    	
	   for (int i = 0 ; i < 3; i ++) { iptest.clickRefreshButton();}
	   
	   
	    
	   
	    
	    String actualIP= iptest.getIpAddress();
	    System.out.println("IP from third party app : "+actualIP);
	    
	    //Assert IP 
	    Assert.assertEquals(actualIP, expected_ip,
	            "IP mismatch! VPN IP and Third-party IP are not the same.");
	    

	
	    driver.terminateApp("cz.webprovider.whatismyipaddress");
	    
	   
	 
	    
	    
	    //Reopen Enova VPN 
	    
	    driver.activateApp("com.enovavpn.mobile");
	    
	    
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
