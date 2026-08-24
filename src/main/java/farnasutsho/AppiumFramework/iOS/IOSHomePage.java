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
 
 
 private By disconnectOnPopup =
		    AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"DISCONNECT\"`][2]");
 
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

    System.out.println("Checking default server: " + server);

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

        System.out.println(
                "Default server matched: " +
                element.getAttribute("name")
        );

        return true;

    } catch (Exception e) {

        System.out.println(
                "Default server does not match: " +
                server
        );

        return false;
    }
}




 
 //Necessary functions 
public void clickconnect() {
	
	clickElement(connectButton);
	
}

public void clickDisconnect() {
	clickElement(disconnectButton );
	
}

public void clickdisconnectOnPopup() {
	clickElement(disconnectOnPopup );
	
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
