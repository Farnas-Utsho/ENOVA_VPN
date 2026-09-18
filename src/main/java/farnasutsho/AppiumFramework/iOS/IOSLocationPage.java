package farnasutsho.AppiumFramework.iOS;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
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
	
	private By ServerSwitch = AppiumBy.accessibilityId("Switch");

	
	public void  clickSwitch() {
		
		clickElement(ServerSwitch);
		
	}



	

	public void SelectCountry(String country) throws InterruptedException {

	    int maxScrolls = 2;
	    By locator;

	    if ("Singapore".equalsIgnoreCase(country)) {

	        locator = AppiumBy.iOSClassChain(
	                "**/XCUIElementTypeButton[`name == \"Singapore\"`][2]"
	        );

	    } else {

	        String predicate =
	                "type == 'XCUIElementTypeButton' AND " +
	                "name == '" + country.trim() + "'";

	        locator = AppiumBy.iOSNsPredicateString(predicate);
	    }

	    for (int i = 0; i < maxScrolls; i++) {

	        List<WebElement> countries = driver.findElements(locator);

	        if (!countries.isEmpty()) {

	            WebElement countryElement = countries.get(0);

	            if (countryElement.isDisplayed()) {
	                countryElement.click();
	                Thread.sleep(1000);
	                return;
	            }
	        }

	        iOSScroll();
	        Thread.sleep(1000);
	    }

	    throw new SkipException(
	            "Country could not be found/displayed after scrolling: "
	                    + country
	    );
	}
	public void SelectServer(String server) throws InterruptedException {

	    String predicate =
	            "type == 'XCUIElementTypeButton' AND " +
	            "name == '" + server.trim() + "'";

	    By locator = AppiumBy.iOSNsPredicateString(predicate);

	    int maxScrolls = 2;

	    for (int i = 0; i < maxScrolls; i++) {

	        List<WebElement> servers = driver.findElements(locator);

	        for (WebElement serverElement : servers) {

	            if (serverElement.isDisplayed()) {
	                serverElement.click();
	                return;
	            }
	        }

	        iOSScroll();
	        Thread.sleep(1000);
	    }

	    throw new SkipException(
	            "Server could not be found/displayed after scrolling: "
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
