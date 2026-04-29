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
public class IOSSettingsPage extends IOSActions{
	
	
	IOSDriver driver ;
	public IOSSettingsPage (IOSDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
		
	}

	
	

//Profile
	
 private By ProfilePage = AppiumBy.accessibilityId("Profile");
  
 
 //Connection settings 
 private By ConnectionSettings = AppiumBy.accessibilityId("Connection settings");

 
//Encryption Protocol 
 private By EncryptionProtocol = AppiumBy.iOSNsPredicateString("name BEGINSWITH 'Encryption protocol'");
 

 //Protocol list Close 
 private By CloseIcon = AppiumBy.iOSNsPredicateString("type == \"XCUIElementTypeImage\"");
 
 
 //Home Icon
 private By HomeIcon = AppiumBy.iOSNsPredicateString("name BEGINSWITH 'Home'");
 
 
//Split tunneling 
private By SplitTunneling = AppiumBy.iOSNsPredicateString("name BEGINSWITH 'Split tunneling'");
 

//Internal Kill switch 
private By killSwitch = AppiumBy.className("XCUIElementTypeSwitch");

// Enable Kill switch
private By EnableButton= AppiumBy.accessibilityId("Enable"); 

//Disable Kill switch
private By DisableButton= AppiumBy.accessibilityId("Disable"); 



 
//Turn On kill switch

public void TurnOnKillSwitch() {
	
	clickConnectionSettings();
	
	clickElement(killSwitch);
	
	clickElement(EnableButton);
	
	gotoSettingspage();
	
	clickElement(HomeIcon);
	

	
}

public void TurnOffKillSwitch() {
	
	clickConnectionSettings();
	
	clickElement(killSwitch);
	
	clickElement(DisableButton);
	
	gotoSettingspage();
	
	clickElement(HomeIcon);
	

	
}

//Click split tunneling

public void clickSplitTunneling() {
	
	clickElement(SplitTunneling);
	
	
}


 //Click on home icon
 
 public void clickHomeIcon() {
	 
	 clickElement(HomeIcon);
	 
 }
 
 
 //Click on connection settings
 public void clickConnectionSettings() {
	 clickElement(ConnectionSettings);
	 
 }

 //Click on Protocol 
 public void clickProtocol() {
	 clickElement(EncryptionProtocol);
	 
 }
 
 //Click on WireGuard Protocol
 
 public void clickVMess() {
	 
	 clickAtCoordinates(675,997);
 }
 
 public void clickWireGuard() {
	 
	 clickAtCoordinates(681,1069);
 }
 
 public void clickClose() {
	 
	 clickElement(CloseIcon);
 }
 
 
 public void gotoSettingspage() {
	 
	 clickAtCoordinates(36,64);
 }
 

 private By splitTunnelInput = AppiumBy.accessibilityId("Type here");
 private By createSplitTunneling= AppiumBy.accessibilityId("Create Split Tunnel");
 private By closelist = AppiumBy.className("XCUIElementTypeImage");

 public void createSplitTunnel() {
	  
	  clickElement(SplitTunneling);
	  driver.findElement(splitTunnelInput).sendKeys("https://whatismyipaddress.com");
	  driver.hideKeyboard();
	  clickElement(createSplitTunneling);
	  clickElement(closelist);
	  
 }

 

	
 
 
}
