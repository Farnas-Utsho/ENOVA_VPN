package farnasutsho.AppiumFramework.IOS;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
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
	
	
	
	@AfterMethod(alwaysRun= true)	
	public void preSetup() {
		driver.terminateApp("com.enovavpn.mobile");
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
		location.SelectCountry("Singapore"); 
		location.SelectServer("Singapore - Premium");
		
		home.clickconnect();
		Thread.sleep(10000);
		
		home.turnoffVPNFromSettings();
		Thread.sleep(8000);
		//Grab the  from the third Party app
		//Check IP from the third Party application
		driver.activateApp("GAAG.myIP"); Thread.sleep(8000); 
		String actualIP= app.extractIP(); 
		System.out.print("Ip from the third party app: " + actualIP ); 
		
		driver.activateApp("com.enovavpn.mobile");
		Thread.sleep(3000);	
		//Turn off the kill Switch
		
		
		
		//Disconnect the vpn 
        Thread.sleep(8000);
	    
	    home.clickDisconnect();
	    home.clickdisconnectOnPopup();

	    String enovaIP = home.extractIP();
	    home.clickCloseConnectionReport();
	    
	    
		Assert.assertEquals(enovaIP, actualIP);
		
		home.clickSettings();
		settings.TurnOffKillSwitch();
		
		
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
		
		location.SelectServer("Japan");
	    
		
		location.clickSwitch();
		Thread.sleep(8000); 
		
		//Verify whether second server has successfully connected or not 
		
		driver.activateApp("GAAG.myIP"); 
		Thread.sleep(8000); 
		String actualIP= app.extractIP(); 
		System.out.print("Ip from the third party app: " + actualIP ); 
		
		driver.activateApp("com.enovavpn.mobile");
		Thread.sleep(3000);	
		//Turn off the kill Switch
		
		
		
		//Disconnect the vpn 
        Thread.sleep(8000);
	    
	    home.clickDisconnect();
	    home.clickdisconnectOnPopup();

	    String enovaIP = home.extractIP();
	    home.clickCloseConnectionReport();
	    
	    
		Assert.assertEquals(enovaIP, actualIP);
		
		
		
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
		driver.get("https://whatismyipaddress.com");
		Thread.sleep(3000);
		String webIP = driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name, '.')]")).getText();
		System.out.println("IP Address from web: " + webIP);
		
		driver.activateApp("com.enovavpn.mobile");
		
		//Disconnect the vpn 
        Thread.sleep(8000);
	    
	    home.clickDisconnect();
	    home.clickdisconnectOnPopup();

	    String enovaIP = home.extractIP();
	    home.clickCloseConnectionReport();
	    
	    
		Assert.assertNotEquals(enovaIP, webIP);
		
	}
	
	
	
	
	

	
	

}
