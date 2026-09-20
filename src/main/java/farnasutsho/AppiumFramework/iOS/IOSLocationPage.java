package farnasutsho.AppiumFramework.iOS;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
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

	    String predicate =
	            "name == '" + country + "' AND " +
	            "label == '" + country + "' AND " +
	            "type == 'XCUIElementTypeButton'";

	    By locatorExact = AppiumBy.iOSNsPredicateString(predicate);

	    By locatorDuplicateIndexed = AppiumBy.iOSClassChain(
	            "**/XCUIElementTypeButton[`name == \"" + country + "\"`][2]"
	    );

	    int maxScrolls = 5;

	    for (int i = 0; i < maxScrolls; i++) {

	        List<WebElement> countries = driver.findElements(locatorExact);

	        if (countries.isEmpty()) {
	            countries = driver.findElements(locatorDuplicateIndexed);
	        }

	        System.out.println("Scroll attempt " + (i + 1) + " for '" + country + "' - matches found: " + countries.size());

	        if (!countries.isEmpty()) {

	            // When the name is shared with a "Quick access" shortcut tile,
	            // the real list row is the last match, not the first.
	            WebElement countryElement = countries.get(countries.size() - 1);

	            if (!countryElement.isDisplayed()) {

	                System.out.println("Matched '" + country + "' but not visible yet, scrolling to it.");

	                scrollToWebElement(countryElement);
	                Thread.sleep(500);

	                countries = driver.findElements(locatorExact);
	                if (countries.isEmpty()) {
	                    countries = driver.findElements(locatorDuplicateIndexed);
	                }

	                if (!countries.isEmpty()) {
	                    countryElement = countries.get(countries.size() - 1);
	                }
	            }

	            if (!countries.isEmpty() && countryElement.isDisplayed()) {

	                System.out.println("Country found on page: " + country);

	                countryElement.click();
	                Thread.sleep(1000);
	                return;
	            }
	        }

	        iOSScroll();
	    }

	    throw new SkipException(
	            "Country could not be found/displayed after scrolling: "
	                    + country
	    );
	}

	public void SelectServer(String server) throws InterruptedException {

	    String predicateWithMs =
	            "type == 'XCUIElementTypeButton' AND " +
	            "name BEGINSWITH '" + server + "' AND " +
	            "name CONTAINS 'ms'";

	    String predicateExact =
	            "name == '" + server + "' AND " +
	            "label == '" + server + "' AND " +
	            "value == '1'";

	    String predicateBeginsWith =
	            "type == 'XCUIElementTypeButton' AND " +
	            "name BEGINSWITH '" + server + "'";

	    By locatorWithMs = AppiumBy.iOSNsPredicateString(predicateWithMs);
	    By locatorExact = AppiumBy.iOSNsPredicateString(predicateExact);
	    By locatorBeginsWith = AppiumBy.iOSNsPredicateString(predicateBeginsWith);

	    int maxScrolls = 5;

	    for (int i = 0; i < maxScrolls; i++) {

	        List<WebElement> servers = driver.findElements(locatorWithMs);

	        if (servers.isEmpty()) {
	            servers = driver.findElements(locatorExact);
	        }

	        if (servers.isEmpty()) {
	            servers = driver.findElements(locatorBeginsWith);
	        }

	        System.out.println("Scroll attempt " + (i + 1) + " for '" + server + "' - matches found: " + servers.size());

	        if (!servers.isEmpty()) {

	            WebElement serverElement = servers.get(0);

	            if (!serverElement.isDisplayed()) {

	                System.out.println("Matched '" + server + "' but not visible yet, scrolling to it.");

	                scrollToWebElement(serverElement);
	                Thread.sleep(500);

	                servers = driver.findElements(locatorWithMs);
	                if (servers.isEmpty()) {
	                    servers = driver.findElements(locatorExact);
	                }
	                if (servers.isEmpty()) {
	                    servers = driver.findElements(locatorBeginsWith);
	                }

	                if (!servers.isEmpty()) {
	                    serverElement = servers.get(0);
	                }
	            }

	            if (!servers.isEmpty() && serverElement.isDisplayed()) {

	                System.out.println("Server found on page: " + server);

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
		String predicate =
		        "type == 'XCUIElementTypeButton' AND " +
		        "name == '" + server + "'";

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
}
