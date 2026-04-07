package farnasutsho.AppiumFramework;

import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import farnasutsho.AppiumFramework.TestUtils.IOSBaseTest;
import io.appium.java_client.AppiumBy;

public class iOSScroll extends IOSBaseTest{
	
	@Test
	public void IOSScrollTest() throws InterruptedException {
	    // Navigate to the screen
	    

	 

	    // Correct way to do long press using mobile: touchAndHold
	    Map<String, Object> params = new HashMap<>();
	       // Important: cast to RemoteWebElement
	    params.put("direction", "down");   
	    
	    driver.executeScript("mobile:scroll", params);
	    
	    driver.findElement(AppiumBy.accessibilityId("Sliders")).click();
	    
	    Thread.sleep(2000);
	}

	
	
}
