package farnasutsho.AppiumFramework.IOSBase;
import farnasutsho.AppiumFramework.Serverlist.Get_Server_List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import farnasutsho.AppiumFramework.iOS.IOSSettingsPage;
import farnasutsho.AppiumFramework.iOS.IOSThirdPartyAPP;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public abstract class BaseServerStatusCheck extends IOSBaseTest{
	
	
	protected AppiumDriver drive;
	
	protected IOSHomePage home;
	protected IOSLocationPage location;
	protected IOSThirdPartyAPP app;
	protected IOSSettingsPage settings;
	
	
	
    protected abstract void selectProtocol();
    protected abstract String getJsonFile();
    protected abstract int getProtocolId();
    protected abstract boolean isWireGuard();
    
	
	
    @BeforeClass
    public void setupProtocol() {

        // =====================================
        // 1. Get latest server list from API
        // =====================================
        Get_Server_List serverList = new Get_Server_List();

        serverList.getServerList(
                getProtocolId(),
                getJsonFile()
        );


        // =====================================
        // 2. Setup iOS application
        // =====================================

        driver.activateApp("com.apple.mobilesafari");
        driver.get("https://api.ipify.org");

        driver.activateApp("com.enovavpn.mobile");

        home = new IOSHomePage(driver);

        settings = home.clickSettings();

        settings.clickConnectionSettings();

        // Select VMess or WireGuard
        selectProtocol();

        settings.clickHome();
    }
	
    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.terminateApp("com.enovavpn.mobile");
        driver.activateApp("com.enovavpn.mobile");
    }

	
	

	
	@Test(dataProvider="getData") 
	public void serverTest(HashMap<String ,String> input) throws InterruptedException {

	 

	    home = new IOSHomePage(driver);
	    location = new IOSLocationPage(driver);
	    app = new IOSThirdPartyAPP(driver);
	    settings= new IOSSettingsPage(driver);

	    
	    String country = input.get("country"); 
	    String server = input.get("server");
	    String expectedIP = input.get("ip");
	    String servercount=input.get("numberofservers");
	     
	    
	      if (home.isDefaultServer(server)) {

	            // default already selected
	            home.clickconnect();

	        } else {

	        	home.goToLocationPage();


	        	  try {
	        		  if ("multiple".equals(servercount)){
	      	        	location.SelectCountry(country);
	      	            location.SelectServer(server);
	      	        } else {
	      	            
	      	            location.SelectServer(server);
	      	        }
	      	    } catch (Exception e) {
	      	        //System.out.println("Server not found: " + server + " | " + e.getMessage());
	      	        throw new SkipException("Skipping test — server not found: " + server);
	      	    }

	        	  home.clickconnect();
	        }


	    
	     
	      
	      
	    driver.activateApp("com.apple.mobilesafari");
	    

	    String actualIP = app.extractIP(); 
	    System.out.println("Actual IP : "+actualIP);

	    driver.activateApp("com.enovavpn.mobile");
	    settings.clickHome();
	    
	    Assert.assertEquals(expectedIP, actualIP);	    
	    
	    home.clickDisconnect();
        home.clickdisconnectOnPopup();

     
  
	    
	}
	
	

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data =
                getJsonData(getJsonFile());

        Object[][] arr = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            arr[i][0] = data.get(i);
        }

        return arr;
    }
	

	

}	
	
	


