package farnasutsho.AppiumFramework.IOS;

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

public abstract class ServerStatusCheck extends IOSBaseTest{
	
	
	protected AppiumDriver drive;
	
	protected IOSHomePage home;
	protected IOSLocationPage location;
	protected IOSThirdPartyAPP app;
	protected IOSSettingsPage settings;
	// Each protocol class will implement these
    protected abstract void selectProtocol();
    protected abstract String getJsonFile();

	
	
	@BeforeClass
	public void setupProtocol() {
	    driver.activateApp("com.apple.mobilesafari");
	    driver.get("https://api.ipify.org");
	    driver.activateApp("com.enovavpn.mobile");
	    home = new IOSHomePage(driver);
        settings = home.clickSettings();

        settings.clickConnectionSettings();
        settings.clickProtocol();
        selectProtocol();
        settings.clickClose();
        settings.clickBackProtocolPage();
        home.clickHomeIcon();
        
	    
	    
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

	    
	    String country = input.get("country"); 
	    String server = input.get("Server"); 
	    
	      if (home.isDefaultServer(server)) {

	            // default already selected
	            home.clickconnect();

	        } else {

	        	home.goToLocationPage();


	        	  try {
	      	        if (country == null || country.trim().isEmpty()) {
	      	            location.SelectServer(server);
	      	        } else {
	      	            location.SelectCountry(country);
	      	            location.SelectServer(server);
	      	        }
	      	    } catch (Exception e) {
	      	        System.out.println("Server not found: " + server + " | " + e.getMessage());
	      	        throw new SkipException("Skipping test — server not found: " + server);
	      	    }

	        	  home.clickconnect();
	        }

	    Thread.sleep(8000);
	    driver.activateApp("com.apple.mobilesafari");

	    String actualIP = app.extractIP(); 
	    System.out.println("Actual IP From : "+actualIP);

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

        List<HashMap<String, String>> data =
                getJsonData(getJsonFile());

        Object[][] arr = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            arr[i][0] = data.get(i);
        }

        return arr;
    }
	

	

}	
	
	


