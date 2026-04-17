package farnasutsho.AppiumFramework;


import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import farnasutsho.AppiumFramework.TestUtils.RealiOS;
import io.appium.java_client.AppiumBy;


public class IOSLogin extends RealiOS{
	
	@Test
	public void IOSBasic() throws InterruptedException {
		
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		    
		    // Wait for LOGIN button to appear and click it
		    wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("LOGIN"))).click();
		    
		    driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`name == \"Email\"`]")).sendKeys("farnas@nagorik.tech");
		    driver.hideKeyboard();
		 
		    wait.until(ExpectedConditions.elementToBeClickable(
		            AppiumBy.iOSClassChain("**/XCUIElementTypeSecureTextField[`name == \"Password\"`]"))
		    ).sendKeys("11223344@Aa");
		    driver.hideKeyboard();
		    driver.hideKeyboard();
		    driver.findElement(AppiumBy.accessibilityId("SIGN IN")).click();
		    
		    Thread.sleep(5000);
		
	}

}
