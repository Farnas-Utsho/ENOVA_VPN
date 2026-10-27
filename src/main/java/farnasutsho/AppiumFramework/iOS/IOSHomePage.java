package farnasutsho.AppiumFramework.iOS;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import farnasutsho.AppiumFramework.utils.IOSActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Hello world!
 */
public class IOSHomePage extends IOSActions{
	
	
	IOSDriver driver ;
	public IOSHomePage (IOSDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
		
	}
	//XCUIElementTypeApplication[@name="EnovaVPN"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeImage[3]
	
	

//Home page locators 
	
 private By locationPage = AppiumBy.iOSNsPredicateString("name Contains 'Auto'");
 
private By connectButton = AppiumBy.accessibilityId("Connected");
 
 // private By connectButton = AppiumBy.xpath("//XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeImage[3]");
 private By disconnectButton = AppiumBy.accessibilityId("DISCONNECT");
 //private By disconnectButton = AppiumBy.xpath("//XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeImage[3]");
 
 
private By popUpCancelButton = AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"xmark\"`]"); 

public void closePopUp() {
	
	
	clickElement(popUpCancelButton);
	
	
}



private By disconnectOnPopup =
		    AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"DISCONNECT\"`][2]");

// "End session ?" confirmation alert shown after tapping the main
// DISCONNECT button (confirmed via Inspector).
private By endSessionAlert = AppiumBy.iOSNsPredicateString(
		"type == 'XCUIElementTypeAlert' AND name == 'End session ?'");

private By ipAddress =
		    AppiumBy.iOSNsPredicateString("name MATCHES '[0-9]{1,3}(\\.[0-9]{1,3}){3}'");
	
private By closeReport = AppiumBy.iOSClassChain("**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeImage[1]")	;
  
//Settings Menu 
private By SettingsIcon = AppiumBy.iOSNsPredicateString("name CONTAINS 'Settings'");
	

private By HomeIcon = AppiumBy.iOSNsPredicateString("Home");

public void clickHomeIcon() {
	
	clickElement(HomeIcon);
	
	
}




public boolean isDefaultServer(String server) {

    String predicate =
            "name BEGINSWITH '" + server + "," + "'";

    By locator =
            AppiumBy.iOSNsPredicateString(predicate);

   // System.out.println("Checking default server: " + server);

    try {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement element = wait.until(driver -> {

            List<WebElement> elements =
                    driver.findElements(locator);

            if (!elements.isEmpty() &&
                elements.get(0).isDisplayed()) {

                return elements.get(0);
            }

            return null;
        });

//        System.out.println(
//                "Default server matched: " +
//                element.getAttribute("name")
//        );

        return true;

    } catch (Exception e) {

//        System.out.println(
//                "Default server does not match: " +
//                server
//        );

        return false;
    }
}




 
 //Necessary functions 
public void clickconnect() {

	clickElement(connectButton);

}

/**
 * Waits for the DISCONNECT button to appear, which only shows once the
 * VPN tunnel is actually up - not just after tapping Connect. Use this
 * before terminating the app, otherwise killing the process too early
 * can tear down a connection that hasn't fully established yet.
 */
public void waitForConnected() {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(disconnectButton));
}

public void clickDisconnect() {
	clickElement(disconnectButton );
	
}

/**
 * Taps DISCONNECT on the "End session ?" confirmation alert. WDA reports
 * this button as visible/enabled/accessible and .click() appears to
 * succeed, but the tap does not reliably register on the native alert
 * overlay - the same issue seen with the "Switch server?" alert in
 * IOSLocationPage.clickSwitch(). Tapping by the element's own on-screen
 * coordinates (rather than calling .click()) works around it.
 */
public void clickdisconnectOnPopup() throws InterruptedException {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	WebElement alert = wait.until(
			org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(endSessionAlert));

	WebElement disconnectButton = wait.until(
			org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(disconnectOnPopup));

	org.openqa.selenium.Rectangle rect = disconnectButton.getRect();
	int tapX = rect.getX() + rect.getWidth() / 2;
	int tapY = rect.getY() + rect.getHeight() / 2;

	driver.executeScript("mobile: tap", java.util.Map.of("x", tapX, "y", tapY));

	Thread.sleep(1000);
}

/**
 * Diagnostic helper: dumps the current page source and a screenshot to
 * the given file path prefix (no extension). Call this right after the
 * disconnect popup appears (before clickdisconnectOnPopup) to capture
 * the real element hierarchy when the popup's DISCONNECT tap is not
 * registering.
 */
public void dumpPageStateForDebug(String filePathPrefix) {

	try {
		String pageSource = driver.getPageSource();
		java.nio.file.Files.writeString(
				java.nio.file.Path.of(filePathPrefix + ".xml"), pageSource);

		byte[] screenshot = driver.getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
		java.nio.file.Files.write(
				java.nio.file.Path.of(filePathPrefix + ".png"), screenshot);

		System.out.println("Dumped page state to " + filePathPrefix + ".xml/.png");
	} catch (Exception e) {
		System.out.println("Failed to dump page state: " + e.getMessage());
	}
}

public void goToLocationPage() {
	
	clickElement(locationPage);
}

public void clickCloseConnectionReport() {
	
	clickElement(closeReport);
	
}


 
	public String extractIP() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    Pattern pattern = Pattern.compile("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");

	    try {
	    	
	    	
	        // wait until ANY element contains a valid IP
	        Boolean ipFound = wait.until(driver -> {
	            List<WebElement> elements = driver.findElements(ipAddress);

	            for (WebElement el : elements) {
	                String text = el.getText();
	                if (text == null) continue;

	                Matcher matcher = pattern.matcher(text);
	                if (matcher.find()) {
	                    return true;
	                }
	            }
	            return false;
	        });

	        if (!ipFound) {
	            return null;
	        }

	        // extract again after wait succeeds
	        List<WebElement> elements = driver.findElements(ipAddress);
	        System.out.println(elements);
	        for (WebElement el : elements) {
	            String text = el.getText();
	            if (text == null) continue;

	            Matcher matcher = pattern.matcher(text);
	            if (matcher.find()) {
	                return matcher.group();
	            }
	        }

	        return null; 

	    } catch (TimeoutException e) {
	        System.out.println("IP not found within timeout");
	        return null;
	    }
	}
	




	
	
	
	
	

	
	public IOSSettingsPage clickSettings() {
		
		clickElement(SettingsIcon);
		
		return new IOSSettingsPage (driver);
	}
	
 
 
}
