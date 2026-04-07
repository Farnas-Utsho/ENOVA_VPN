package farnasutsho.AppiumFramework.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Hello world!
 */
public class IOSActions extends AppiumUtils{
	
            IOSDriver driver ;
	
	

	
		// Here all the android actions code  need to enlist 
	public void IOSLongPressTest() {
	    // Navigate to the screen
	    driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Steppers\"`]")).click();

	    WebElement ele = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Increment\"`]"));

	    // Correct way to do long press using mobile: touchAndHold
	    Map<String, Object> params = new HashMap<>();
	    params.put("element", ((RemoteWebElement) ele).getId());   // Important: cast to RemoteWebElement
	    params.put("duration", 5);   // duration in seconds (e.g. 1.5 seconds)

	    driver.executeScript("mobile: touchAndHold", params);
	}
  
  
}
