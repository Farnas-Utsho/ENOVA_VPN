package farnasutsho.AppiumFramework.IOS;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import farnasutsho.AppiumFramework.TestUtils.AndroidBaseTest;
import farnasutsho.AppiumFramework.TestUtils.IOSBaseTest;
import farnasutsho.AppiumFramework.android.HomePage;
import farnasutsho.AppiumFramework.android.LocationPage;
import farnasutsho.AppiumFramework.android.myIPappPage;
import farnasutsho.AppiumFramework.iOS.IOSHomePage;
import farnasutsho.AppiumFramework.iOS.IOSLocationPage;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class ServerStatusCheck_Test extends IOSBaseTest{
	
	public AppiumDriver drive;
	
	public IOSHomePage home;
	public IOSLocationPage location;
	
	
		
	
	@Test
	public void serverTest() throws Exception {

		home = new IOSHomePage(driver);
		
		location = new IOSLocationPage(driver);
	  

		
		  
		home.GoToServerList();
		
		
		
		location.SelectCountry("Germany");
		location.SelectServer("Germany - 6");

		
		home.ClickConnect();
		// wait for VPN connection (replace sleep later with better wait)
	    Thread.sleep(8000);
	    
	  

	    Thread.sleep(8000);
	    
		
	    String extractedIP = home.extractIP();
	    
	    System.out.println(extractedIP);
	    
	    
	    
	    home.ClickDisconnect();
		
	
	 
	    
	    
	}
	
	
	
	public String getIPFromiPad() {

	    driver.activateApp("com.apple.mobilesafari");

	    driver.get("https://api.ipify.org");

	    // Give Safari/WebKit time to load VPN-routed IP
	    try {
	        Thread.sleep(4000);
	    } catch (InterruptedException ignored) {}

	    String source = driver.getPageSource();

	    String ip = source.replaceAll("<.*?>", "").trim();
	    
	    
	    System.out.println(ip);

	    if (ip.isEmpty()) {
	        throw new RuntimeException("Empty IP received from iPad Safari");
	    }

	    return ip;
	}
	
	
	
//	@DataProvider
//	public Object[][] getData() throws IOException {
//	    List<HashMap<String, String>> data = getJsonData(
//	        System.getProperty("user.dir") + "/src/test/java/farnasutsho/AppiumFramework/testData/serverlist.json"
//	    );
//
//	    Object[][] arr = new Object[data.size()][1]; // 1 parameter per row
//
//	    for (int i = 0; i < data.size(); i++) {
//	        arr[i][0] = data.get(i); // each HashMap is a separate test row
//	    }
//
//	    return arr;
//	}

	
	

	
	

}
