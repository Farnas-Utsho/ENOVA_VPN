package farnasutsho.AppiumFramework.IOS;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import farnasutsho.AppiumFramework.TestUtils.IOSBaseTest;
import farnasutsho.AppiumFramework.iOS.IOSHomePage;
import farnasutsho.AppiumFramework.iOS.IOSLocationPage;
import farnasutsho.AppiumFramework.iOS.IOSSettingsPage;
import farnasutsho.AppiumFramework.iOS.IOSThirdPartyAPP;

import io.appium.java_client.AppiumDriver;

public class EnovaVPN_test extends IOSBaseTest{
	
	
	public AppiumDriver drive;
	
	public IOSHomePage home;
	public IOSLocationPage location;
	public IOSThirdPartyAPP app;
	public IOSSettingsPage settings;
	
	
	
	
	@BeforeClass
	public void setupSafari() {
	    driver.activateApp("com.apple.mobilesafari");
	    driver.get("https://api.ipify.org");
	}

	
	@BeforeMethod(alwaysRun= true)		
	public void Setup() {
		driver.terminateApp("com.enovavpn.mobile");
		driver.activateApp("com.enovavpn.mobile");
	
		
	}
	
	@AfterMethod(alwaysRun= true)	
	public void preSetup() {
		driver.activateApp("com.enovavpn.mobile");
	}
	
	@Test
	public void KillSwitchTest() throws InterruptedException {
		
		home = new IOSHomePage(driver);
		
		settings = home.clickSettings();
		
		location = new IOSLocationPage(driver);
		app = new IOSThirdPartyAPP(driver);
		settings.clickConnectionSettings();
		
		settings.TurnOnKillSwitch();
		
		home.goToLocationPage();
		location.SelectServer("Brazil");
		
		home.clickconnect();
		Thread.sleep(10000);
		
		home.turnoffVPNFromSettings();
		Thread.sleep(8000);
		//Grab the  from the third Party app
		//Check IP from the third Party application
		
		Thread.sleep(8000);
		driver.activateApp("com.apple.mobilesafari");
		String actualIP= app.extractIP(); 
		System.out.print("Ip from the third party app: " + actualIP ); 
		
		driver.activateApp("com.apple.mobilesafari");
		String actualIPorginal= app.extractIP(); 
		System.out.print("Ip from the third party app: " + actualIPorginal ); 
		
		driver.activateApp("com.enovavpn.mobile");
		Thread.sleep(3000);	
		//Turn off the kill Switch
		
		
		
		//Disconnect the vpn 
        Thread.sleep(8000);
	    
	    home.clickDisconnect();
	    home.clickdisconnectOnPopup();

	    String enovaIP = home.extractIP();
	    
		
	    home.clickCloseConnectionReport();
	    
	    
	    home.clickSettings();
		settings.TurnOffKillSwitch();
		
		Assert.assertEquals(enovaIP, actualIP);
	    
		
		
		
		
	}
	
	
	@Test
	public void ServerSwitch_Test() throws InterruptedException {
		
		
		home = new IOSHomePage(driver);
		location = new IOSLocationPage(driver);
		app = new IOSThirdPartyAPP(driver);
		
		
		//Connect with any server - > First server validation
		home.goToLocationPage();
		
		location.SelectServer("United Kingdom");
		
		home.clickconnect();
		
		//Switch to second server 
        home.goToLocationPage();
    	Thread.sleep(8000); 
        
        
		location.SelectServerSwitch("Brazil");
	    
		
		location.clickSwitch();
	
		
		//Verify whether second server has successfully connected or not 
		
		    Thread.sleep(8000);
		    driver.activateApp("com.apple.mobilesafari");

		    String actualIP = app.extractIP(); 
		    System.out.println("Actual IP From : "+actualIP);
		
		  driver.activateApp("com.enovavpn.mobile");
		    Thread.sleep(8000);
		    
		    home.clickDisconnect();
	        home.clickdisconnectOnPopup();

		    String enovaIP = home.extractIP();
		    Assert.assertEquals(enovaIP, actualIP);
	        home.clickCloseConnectionReport();

		
	}
	
	
	@Test
	public void SplitTunneling_Test() throws InterruptedException {
		home = new IOSHomePage(driver); 
		location = new IOSLocationPage(driver); 
		app = new IOSThirdPartyAPP(driver) ; 
		settings= new IOSSettingsPage(driver);
		
		home.clickSettings();
		settings.clickConnectionSettings();
		settings.createSplitTunnel();
		
		settings.gotoSettingspage();
		settings.clickHomeIcon();
		
		home.goToLocationPage();
		location.SelectServer("United Kingdom");
		home.clickconnect();
		
		driver.activateApp("com.apple.mobilesafari");
		Thread.sleep(3000); // Wait for Safari to load
		
		// Perform actions in Safari
		  Thread.sleep(8000);
		    driver.activateApp("com.apple.mobilesafari");

		    String WebIP = app.extractIP(); 
		    System.out.println("Actual IP From : "+WebIP);
		
		   driver.activateApp("com.enovavpn.mobile");
		    Thread.sleep(8000);
		


	    home.clickDisconnect();
	    home.clickdisconnectOnPopup();

	    String enovaIP = home.extractIP();
	    Assert.assertNotEquals(enovaIP, WebIP);
	    home.clickCloseConnectionReport();
	    
	    home.clickSettings();
		settings.clickConnectionSettings();
		settings.removeSplitTunneling();
		
	    
		
		
	}
	
	
	
	
	

	
	

}
