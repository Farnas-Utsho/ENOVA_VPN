package farnasutsho.AppiumFramework;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import farnasutsho.AppiumFramework.TestUtils.AndroidBaseTest;
import io.appium.java_client.AppiumBy;



public class AppiumBasics extends AndroidBaseTest{
		@Test
		public void AppiumTest() throws MalformedURLException, URISyntaxException {
			//AndroidDriver , IOSDriver
	
			driver.findElement(AppiumBy.accessibilityId("Preference")).click();
			
			
}
	
}
