package farnasutsho.AppiumFramework.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Hello world!
 */
public class IOSActions extends AppiumUtils{
	
            IOSDriver driver ;
	
           

	
        	public IOSActions(IOSDriver driver)
        	{
        	
        		this.driver = driver;
        	}
        	
        	public void longPressAction(WebElement ele)
        	{
        		Map <String,Object>params = new HashMap<>();
        		params.put("element", ((RemoteWebElement)ele).getId());
        		params.put("duration", 5);
        			
        		driver.executeScript("mobile:touchAndHold", params);
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
        	
        	public void scrollToWebElement(WebElement ele)
        	{
        		
        		Map<String,Object> params = new HashMap<>();
        		params.put("direction", "down");		
        		params.put("element", ((RemoteWebElement)ele).getId());
        		
        		driver.executeScript("mobile:scroll", params);
        	}
        	
        	
        	public void swipeAction(WebElement ele,String direction)
        	{
        		Map<String,Object> params1 = new HashMap<String,Object> ();
        		params1.put("direction","left");
        		//params1.put("element", ((RemoteWebElement)ele).getId());
        		driver.executeScript("mobile:swipe", params1);
        		
        		
        	}

        	
        	
        	public void iOSScroll() throws InterruptedException {
        		
        	    Map<String, Object> params = new HashMap<>();
     	       // Important: cast to RemoteWebElement
     	    params.put("direction", "down");   
     	    
     	    driver.executeScript("mobile:scroll", params);
     	    
     	    
     	    
     	    Thread.sleep(2000);
     	}
        		
        		
        		
        		
        		
        		
        	}
  
  

