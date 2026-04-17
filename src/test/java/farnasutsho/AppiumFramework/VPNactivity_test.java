package farnasutsho.AppiumFramework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import farnasutsho.AppiumFramework.TestUtils.AndroidBaseTest;
import farnasutsho.AppiumFramework.android.HomePage;

import farnasutsho.AppiumFramework.android.LocationPage;
import farnasutsho.AppiumFramework.android.myIPappPage;
import io.appium.java_client.AppiumDriver;



public class VPNactivity_test extends AndroidBaseTest{
	
	public AppiumDriver drive;
	
	
	public HomePage home;
	
	public LocationPage location;
	
	public myIPappPage iptest;
	
	
//	
	
//	@AfterMethod
//	public void presetup() throws InterruptedException {
//		driver.terminateApp("com.enovavpn.mobile");
//	     
//		Thread.sleep(1000);
//	     
//	     driver.activateApp("com.enovavpn.mobile"); 
//	   
//	}
	
	
	
	
	
	@Test
	public void sreverSwitch() throws InterruptedException {
		
		
		home = new HomePage(driver);
	    location = new LocationPage(driver); 
	    iptest = new myIPappPage(driver);
		
		//GO to server list select any server 
		
		home.GoToServerList();
		
		//Select any server under any drop down 
		
		location.SelectCountry("France");
		
		location.SelectServer("France - 5");
		
		//Connect with server and and collect the IP address 
		
		home.clickConnect();
		Thread.sleep(5000);
		String expected_ip = home.VPNiPAddress();
		
		
	
		
		
		Thread.sleep(10000);
		
		
		
		
	    //Collect IP from third Party Application
	    driver.activateApp("cz.webprovider.whatismyipaddress");
	    Thread.sleep(2000);
	    

	    iptest.clickRefreshButton();
		   

	    for(int i = 0 ; i < 3 ;i++) {
	    	Thread.sleep(2000);
	    	iptest.clickRefreshButton();
		    

	    Thread.sleep(1000);
	    
	    String actualIP= iptest.getIpAddress();
	    System.out.println("IP from third party app : "+actualIP);
	    driver.terminateApp("cz.webprovider.whatismyipaddress");
	    
	    
	  //Assert IP 
	    AssertJUnit.assertEquals(actualIP, expected_ip, 
	    	    "IP mismatch! VPN IP and Third-party IP are not the same.");
	    
	    
	    
	    
	    
		//Again open the Enova VPN
		
	    driver.activateApp("com.enovavpn.mobile"); 
		
	    home.GoToServerList();
	    
	    //Select another server 
	    location.SelectServer("Japan");
	    
	    //switch server 
	    
	    location.ClickSwitchServer();
	    Thread.sleep(5000);
	    
	    //Collect iP address
	    String expected_ip_2nd = home.VPNiPAddress();
		//Terminate the vpn application 
	
		
		
		Thread.sleep(10000);
		
		
		
	    //Collect IP from third Party Application
	    driver.activateApp("cz.webprovider.whatismyipaddress");
	    Thread.sleep(2000);
	    
	    for(int i1 = 0 ; i1 < 3 ;i1++) {
	    	Thread.sleep(2000);
	    	iptest.clickRefreshButton();
		    
	    }
	    Thread.sleep(1000);
	    
	    String actualIP_2nd= iptest.getIpAddress();
	    System.out.println("IP from third party app : "+actualIP);
	    driver.terminateApp("cz.webprovider.whatismyipaddress");
	    
	    
	  //Assert IP 
	    AssertJUnit.assertEquals(actualIP_2nd, expected_ip_2nd, 
	    	    "IP mismatch! VPN IP and Third-party IP are not the same.");

	    
	    //Disconnect enova 
	    
	    home.clickDisConnect();
	    home.ClickDisconnectOnPopUp();
	    home.connectionReportPopClose();
	    
		
		
	
		
	}
	
	
	
	
	
	
	
	
	

	
	

	}}
