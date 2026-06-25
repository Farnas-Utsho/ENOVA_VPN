package farnasutsho.AppiumFramework.iOS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import farnasutsho.AppiumFramework.utils.IOSActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumBy.ById;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Hello world!
 */
public class IOSLocationPage extends IOSActions{
	
	IOSDriver driver ;
	public IOSLocationPage(IOSDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
		
	}
	
	private By ServerSwitch = AppiumBy.accessibilityId("Switch");

	
	public void  clickSwitch() {
		
		clickElement(ServerSwitch);
		
	}



  
	public void SelectCountry(String country) throws InterruptedException {

	    String predicate = "name == '" + country + "'";
	    By locator = AppiumBy.iOSNsPredicateString(predicate);

	    // Check if already visible
	    if (driver.findElements(locator).isEmpty()) {
	        iOSScroll();
	    }

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement element = wait.until(
	            ExpectedConditions.elementToBeClickable(locator));

	    element.click();
	}
	

	public void SelectServer(String server) throws InterruptedException {

	    String predicate =
	            "name BEGINSWITH '" + server + "' AND name CONTAINS 'ms'";

	    By locator = AppiumBy.iOSNsPredicateString(predicate);

	    // Scroll only if not currently visible
	    if (driver.findElements(locator).isEmpty()) {
	        iOSScroll();
	    }

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement element = wait.until(
	            ExpectedConditions.elementToBeClickable(locator));

	    element.click();
	}
	
	
	public void SelectServerSwitch(String server) throws InterruptedException {

	    iOSScroll();

	    
	    String predicate =
	        "name BEGINSWITH '" + server + "' OR name CONTAINS 'ms'";

	    driver.findElement(AppiumBy.iOSNsPredicateString(predicate)).click();
	}
  
  
  
  
}
