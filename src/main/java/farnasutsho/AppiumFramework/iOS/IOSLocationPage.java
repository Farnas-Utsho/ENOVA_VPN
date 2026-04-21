package farnasutsho.AppiumFramework.iOS;

import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;


import farnasutsho.AppiumFramework.utils.IOSActions;
import io.appium.java_client.AppiumBy;
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




  
  
	public void SelectCountry(String country) throws InterruptedException {

	    iOSScroll();
	    
	    String predicate = "name == '" + country + "'";

	    driver.findElement(AppiumBy.iOSNsPredicateString(predicate)).click();
	}
	

	public void SelectServer(String server) throws InterruptedException {

	    iOSScroll();

	    String predicate =
	        "name BEGINSWITH '" + server + "' AND name CONTAINS 'ms'";

	    driver.findElement(AppiumBy.iOSNsPredicateString(predicate)).click();
	}
  
  
  
  
}
