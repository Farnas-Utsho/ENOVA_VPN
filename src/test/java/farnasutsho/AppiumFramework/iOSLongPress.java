package farnasutsho.AppiumFramework;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import farnasutsho.AppiumFramework.TestUtils.IOSBaseTest;
import io.appium.java_client.AppiumBy;

public class iOSLongPress extends IOSBaseTest{
	
	@Test
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
