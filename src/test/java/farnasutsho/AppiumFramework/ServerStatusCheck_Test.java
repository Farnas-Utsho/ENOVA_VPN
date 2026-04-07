package farnasutsho.AppiumFramework;

import org.testng.annotations.Test;

import farnasutsho.AppiumFramework.TestUtils.AndroidBaseTest;
import farnasutsho.AppiumFramework.android.HomePage;
import farnasutsho.AppiumFramework.android.LandingPage;
import farnasutsho.AppiumFramework.android.LocationPage;

public class ServerStatusCheck_Test extends AndroidBaseTest{
	
	public HomePage home;
	
	public LocationPage location;
	
	@Test
	public void serverTest() throws InterruptedException {
		
		 home = new HomePage(driver);
		 location = new LocationPage(driver); 
		
		
		
	
		
		
		
		
		home.GoToServerList();
		location.SelectCountry("USA");
		location.SelectCountry("United Kingdom");
		Thread.sleep(5000);
		home.clickConnect();
		Thread.sleep(5000);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	

	
	

	
	
	

}
