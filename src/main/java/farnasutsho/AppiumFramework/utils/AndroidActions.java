package farnasutsho.AppiumFramework.utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Hello world!
 */
public class AndroidActions extends AppiumUtils{
	
	AndroidDriver driver ;
	
	
	
	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	
		// Here all the android actions code  need to enlist 
		  
}
	
	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
						"duration",2000));
	}
	
	
	public WebElement getCountryByName(String countryName) {
	    return driver.findElement(AppiumBy.androidUIAutomator(
	        "new UiScrollable(new UiSelector().scrollable(true))" +
	        ".scrollIntoView(new UiSelector().descriptionContains(\"" + countryName + "\"));"
	    ));
	}
	
	public WebElement selectServer(String serverName) {
	    WebElement serverElement = driver.findElement(AppiumBy.androidUIAutomator(
	        "new UiScrollable(new UiSelector().scrollable(true))" +
	        ".scrollIntoView(new UiSelector().descriptionContains(\"" + serverName + "\"));"
	    ));
	    return serverElement;
	}

	public void scrollToEndAction()
	{
		boolean canScrollMore;
		do
		{
		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			    
			));
		}while(canScrollMore);
	}
	
	public void scrollToText(String text)
	{
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView((\""+text+"\"));"));
	}
	
	public WebElement scrollToContent(String contentDesc) {
	    return driver.findElement(AppiumBy.androidUIAutomator(
	        "new UiScrollable(new UiSelector().scrollable(true))" +
	        ".scrollIntoView(new UiSelector().descriptionContains(\"" + contentDesc + "\"));"
	    ));
	}
	
	
	public void swipeAction(WebElement ele,String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			 
			    "direction", direction,
			    "percent", 0.75
			));
		
		
	}
	
	@AndroidFindBy(accessibility="Next")
	private WebElement NextButton;
	
	public void ClickNext() {
		
		NextButton.click();
		
		
	}
	
	
	
}