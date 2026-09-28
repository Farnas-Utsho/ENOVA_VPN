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
	
	// CONTINUE itself is reported visible=false / accessible=false by WDA
	// (confirmed via Inspector) - the app excludes it from the
	// accessibility tree entirely, so no findElement() strategy can ever
	// locate it. Only the alert container itself is reliably findable;
	// CONTINUE is tapped by coordinates relative to the alert's own rect
	// (see clickSwitch()).
	private By SwitchServerAlert = AppiumBy.iOSNsPredicateString(
	        "type == 'XCUIElementTypeAlert' AND name == 'Switch server?'"
	);

	public void  clickSwitch() throws InterruptedException {

		System.out.println("[clickSwitch] waiting for 'Switch server?' alert to be present...");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement alert = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(SwitchServerAlert));

		System.out.println("[clickSwitch] alert confirmed present.");

		// CONTINUE is rendered on screen but reported as
		// visible=false / accessible=false by WDA - it is deliberately
		// excluded from the accessibility tree by the app itself, so no
		// findElement() strategy (accessibility id, class chain, xpath)
		// can ever locate it. Tap its known on-screen position directly
		// (from a live Inspector read: x=191, y=443, width=141, height=48
		// -> center 261, 467). This is tied to this device/screen size;
		// if the alert ever renders at a different position or on a
		// different screen size, these coordinates need updating.
		org.openqa.selenium.Rectangle alertRect = alert.getRect();
		System.out.println("[clickSwitch] alert rect: " + alertRect);

		int tapX = 261;
		int tapY = 467;

		System.out.println("[clickSwitch] tapping CONTINUE at (" + tapX + ", " + tapY + ")");

		driver.executeScript("mobile: tap", java.util.Map.of("x", tapX, "y", tapY));

		Thread.sleep(1000);

		try {
			List<WebElement> alertAfterTap = driver.findElements(SwitchServerAlert);
			System.out.println("[clickSwitch] after tap - alert still present: " + !alertAfterTap.isEmpty());
		} catch (Exception e) {
			System.out.println("[clickSwitch] could not re-check alert state after tap (" + e.getClass().getSimpleName() + "), assuming it closed.");
		}
	}

	/**
	 * isDisplayed() alone is not reliable for rows far below the fold:
	 * some table cells report displayed == true before they have actually
	 * scrolled into the visible viewport. Cross-check against the element's
	 * real on-screen bounds vs the device window size.
	 */
	private boolean isActuallyVisible(WebElement element) {

	    if (!element.isDisplayed()) {
	        return false;
	    }

	    try {
	        org.openqa.selenium.Rectangle rect = element.getRect();
	        int screenHeight = driver.manage().window().getSize().getHeight();

	        return rect.getY() >= 0
	                && rect.getY() < screenHeight
	                && (rect.getY() + rect.getHeight()) <= screenHeight + 5;

	    } catch (Exception e) {
	        return false;
	    }
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

	        String matchedBy = "exact";
	        List<WebElement> countries = driver.findElements(locatorExact);

	        if (countries.isEmpty()) {
	            matchedBy = "duplicateIndexed";
	            countries = driver.findElements(locatorDuplicateIndexed);
	        }

	        System.out.println("Scroll attempt " + (i + 1) + " for '" + country + "' - matches found: " + countries.size() + " (via " + matchedBy + ")");

	        if (!countries.isEmpty()) {

	            // When the name is shared with a "Quick access" shortcut tile,
	            // the real list row is the last match, not the first.
	            WebElement countryElement = countries.get(countries.size() - 1);

	            if (!isActuallyVisible(countryElement)) {

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

	            if (!countries.isEmpty() && isActuallyVisible(countryElement)) {

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

	    By locatorDuplicateIndexed = AppiumBy.iOSClassChain(
	            "**/XCUIElementTypeButton[`name == \"" + server + "\"`][2]"
	    );

	    int maxScrolls = 5;

	    for (int i = 0; i < maxScrolls; i++) {

	        String matchedBy = "withMs";
	        List<WebElement> servers = driver.findElements(locatorWithMs);

	        if (servers.isEmpty()) {
	            matchedBy = "exact";
	            servers = driver.findElements(locatorExact);
	        }

	        if (servers.isEmpty()) {
	            matchedBy = "beginsWith";
	            servers = driver.findElements(locatorBeginsWith);
	        }

	        if (servers.isEmpty()) {
	            matchedBy = "duplicateIndexed";
	            servers = driver.findElements(locatorDuplicateIndexed);
	        }

	        System.out.println("Scroll attempt " + (i + 1) + " for '" + server + "' - matches found: " + servers.size() + " (via " + matchedBy + ")");

	        if (!servers.isEmpty()) {

	            // When the name is shared with another element (e.g. a
	            // Quick access / Recommended shortcut), the real list row
	            // is the last match, not the first.
	            WebElement serverElement = servers.get(servers.size() - 1);

	            if (!isActuallyVisible(serverElement)) {

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
	                if (servers.isEmpty()) {
	                    servers = driver.findElements(locatorDuplicateIndexed);
	                }

	                if (!servers.isEmpty()) {
	                    serverElement = servers.get(servers.size() - 1);
	                }
	            }

	            if (!servers.isEmpty() && isActuallyVisible(serverElement)) {

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

	    // While already connected (switch-server screen), some server rows
	    // are the second match for their name in the accessibility tree
	    // (a duplicate), while others (e.g. "USA - 10") only match once.
	    // Try the [2]-indexed class chain first, then fall back to a
	    // plain, unindexed match.
	    By locatorDuplicateIndexed = AppiumBy.iOSClassChain(
	            "**/XCUIElementTypeButton[`name == \"" + server + "\"`][2]"
	    );

	    By locatorExact = AppiumBy.iOSNsPredicateString(
	            "name == '" + server + "' AND label == '" + server + "' AND type == 'XCUIElementTypeButton'"
	    );

	    int maxScrolls = 5;

	    for (int i = 0; i < maxScrolls; i++) {

	        List<WebElement> servers = driver.findElements(locatorDuplicateIndexed);

	        if (servers.isEmpty()) {
	            servers = driver.findElements(locatorExact);
	        }

	        if (!servers.isEmpty()) {

	            WebElement serverElement = servers.get(0);

	            if (isActuallyVisible(serverElement)) {

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
