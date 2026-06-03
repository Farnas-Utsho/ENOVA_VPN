package farnasutsho.AppiumFramework.IOS;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.net.*;
import java.io.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import farnasutsho.AppiumFramework.TestUtils.IOSBaseTest;
import farnasutsho.AppiumFramework.iOS.IOSLocationPage;
import farnasutsho.AppiumFramework.iOS.IOSLoginPage;
import farnasutsho.AppiumFramework.iOS.IOSThirdPartyAPP;
import io.appium.java_client.AppiumBy;
public class EnovaLogin_Test extends IOSBaseTest{
	
	

	public IOSLoginPage login;
	public IOSLocationPage location;
	
	public IOSThirdPartyAPP app;

	
	@AfterMethod
	public void postSetup() {
	    if (driver != null) {
	        driver.terminateApp("com.enovavpn.mobile");
	        driver.activateApp("com.enovavpn.mobile");
	    }
	}
	
	//@Test(dataProvider="getData")
	public void invalid_LoginTest(HashMap<String ,String> input) {
		
			login = new IOSLoginPage(driver);
			
			
		    String email = input.get("email"); 
		    String password = input.get("password"); 
			
			login.clickLogIn();
			login.InputEmail(email);
			login.InputPassword(password);
			login.ClickSignIn();
			By errorMessage =
				    AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeOther' AND visible == 1");

				WebElement error = driver.findElement(errorMessage);
				Assert.assertTrue(error.isDisplayed());
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
	    List<HashMap<String, String>> data = getJsonData(
	    		System.getProperty("user.dir")
	    		+ "/src/test/java/farnasutsho/AppiumFramework/testData/loginValidation.json"
	    );

	    Object[][] arr = new Object[data.size()][1]; // 1 parameter per row

	    for (int i = 0; i < data.size(); i++) {
	        arr[i][0] = data.get(i); // each HashMap is a separate test row
	    }

	    return arr;
	}
	
	
	public void ipCollect() {
	


		
	}
	
	public String extractIP() {
	    try {
	        URL url = new URL("https://api.ipify.org");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");

	        BufferedReader reader =
	                new BufferedReader(new InputStreamReader(conn.getInputStream()));

	        String ip = reader.readLine();
	        reader.close();

	        return ip;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}	
	

}