package farnasutsho.AppiumFramework.iOS;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

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



	

	public void SelectCountry(String country)
	        throws InterruptedException {

	    int maxScrolls = 2;

	    if ("Singapore".equalsIgnoreCase(country)) {

	        String classChain =
	                    "**/XCUIElementTypeButton[`name == 'Singapore'`][2]";
	        

	        By locator = AppiumBy.iOSClassChain(classChain);

	        for (int i = 0; i < maxScrolls; i++) {

	            List<WebElement> countries =
	                    driver.findElements(locator);

//	            System.out.println(
//	                    "Attempt " + (i + 1) +
//	                    " | Singapore found: " + countries.size()
//	            );

	            if (!countries.isEmpty()) {

	                WebElement countryElement =
	                        countries.get(0);
//
//	                System.out.println(
//	                        "Country: " +
//	                        countryElement.getAttribute("name")
//	                );

//	                System.out.println(
//	                        "Displayed: " +
//	                        countryElement.isDisplayed()
//	                );

	                if (countryElement.isDisplayed()) {

//	                    System.out.println(
//	                            "Singapore is visible. Clicking..."
//	                    );

	                    countryElement.click();
	                    Thread.sleep(1000);
	                    return;
	                }
	            }

//	            System.out.println(
//	                    "Singapore not visible. Scrolling..."
//	            );

	            iOSScroll();
	        }

	    } else {

	        // Other countries → existing Predicate
	        String predicate =
	                "name == '" + country + "' AND " +
	                "label == '" + country + "' AND " +
	                "type == 'XCUIElementTypeButton'";

	        By locator =
	                AppiumBy.iOSNsPredicateString(predicate);

	        for (int i = 0; i < maxScrolls; i++) {

	            List<WebElement> countries =
	                    driver.findElements(locator);

//	            System.out.println(
//	                    "Attempt " + (i + 1) +
//	                    " | Country found: " + countries.size()
//	            );

	            if (!countries.isEmpty()) {

	                WebElement countryElement =
	                        countries.get(0);

	                if (countryElement.isDisplayed()) {

	                    countryElement.click();
	                    Thread.sleep(1000);
	                    return;
	                }
	            }

	            iOSScroll();
	        }
	    }

	    throw new SkipException(
	            "Country could not be found/displayed after scrolling: "
	                    + country
	    );
	}

	public void SelectServer(String server) throws InterruptedException {

	    String predicate =
	            "type == 'XCUIElementTypeButton' AND " +
	            "name BEGINSWITH '" + server + "' AND " +
	            "name CONTAINS 'ms'";

	    By locator = AppiumBy.iOSNsPredicateString(predicate);

	    WebDriverWait wait =
	            new WebDriverWait(driver, Duration.ofSeconds(30));

	    int maxScrolls = 2;

	    for (int i = 0; i < maxScrolls; i++) {

	        List<WebElement> servers = driver.findElements(locator);



	        if (!servers.isEmpty()) {

	            WebElement serverElement = servers.get(0);

	            if (serverElement.isDisplayed()) {



	                serverElement.click();
	                
	                return;
	            }
	        }

	        iOSScroll();

	        Thread.sleep(1000);
	    }

	    throw new SkipException(
	            "Server could not be found/ displayed after scrolling: "
	            + server
	    );
	}
	
	public void SelectServerSwitch(String server) throws InterruptedException {

	    iOSScroll();

	    
	    String predicate =
	        "name BEGINSWITH '" + server + "' OR name CONTAINS 'ms'";

	    driver.findElement(AppiumBy.iOSNsPredicateString(predicate)).click();
	}
  
  
  
  
}
