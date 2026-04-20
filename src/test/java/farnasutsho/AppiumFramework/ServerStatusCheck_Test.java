package farnasutsho.AppiumFramework;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import farnasutsho.AppiumFramework.TestUtils.AndroidBaseTest;
import farnasutsho.AppiumFramework.android.HomePage;
import farnasutsho.AppiumFramework.android.LocationPage;
import farnasutsho.AppiumFramework.android.myIPappPage;



public class ServerStatusCheck_Test extends AndroidBaseTest{

	

	

	public HomePage home;
	
	public LocationPage location;
	
	public myIPappPage iptest;
	
	
	
	
	
	
	
	
	

	@AfterMethod
	public void presetup() {
	    
	        
		driver.terminateApp("com.enovavpn.mobile");
		
		driver.activateApp("com.enovavpn.mobile");
	

	}
	     
	     
	   
	
	
	@Test(dataProvider="getData")
	public void serverTest(HashMap<String ,String> input) throws InterruptedException {

		    HomePage home = new HomePage(driver);
		    LocationPage location = new LocationPage(driver);
		    myIPappPage iptest = new myIPappPage(driver);	    
	    

	    String country = input.get("country");
	    String server = input.get("Server");
	    
	    
	 

	  

	   

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
	    System.out.println("Selecting server : "+server);
	     
	    location.SelectServer(server);

	    home.clickConnect();
	    humanPause();// wait a bit before connecting
	    
	    //From here code for the third Party app starts
	    
	    
	    //Collect IP from third Party Application
	    driver.activateApp("cz.webprovider.whatismyipaddress");
	    humanPause();
	  
	    
	    String actualIP = iptest.getIpAddress();
	    
	    System.out.println("Ip from the third party app: "+actualIP);
	    

	    
	    
	    driver.activateApp("com.enovavpn.mobile");
	   
	    humanPause();
	    
	    // wait a bit before connecting
	    String expected_ip = home.VPNiPAddress();
		   
		   
	    System.out.println("IP from vpn applicatoin : "+expected_ip);
	    
	  //Assert IP 
	    Assert.assertEquals(actualIP, expected_ip, 
	    	    "IP mismatch! VPN IP and Third-party IP are not the same."); 
	    
	    
	    
	    home.clickDisConnect();
	    
	  
	    // Handle pop ups if any
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