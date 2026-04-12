package farnasutsho.AppiumFramework;


import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import farnasutsho.AppiumFramework.TestUtils.IOSBaseTest;
import io.appium.java_client.AppiumBy;


public class IOSBasics extends IOSBaseTest{
	
	@Test
	public void IOSBasic() {
		
		
		driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
		
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == \"Text Entry\"`]")).click();
		
		driver.findElement(AppiumBy.iOSNsPredicateString("type == \"XCUIElementTypeTextField\"")).sendKeys("Hello World");
		
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"OK\"`]")).click();
		
		
	}

}
