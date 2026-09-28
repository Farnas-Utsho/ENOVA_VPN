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

 


 
 //Settings Page Back
 private By BackButton = AppiumBy.accessibilityId("BackButton");
 
//Speed Checking 
 
 private By SpeedTestButton = AppiumBy.iOSNsPredicateString("name BEGINSWITH 'Speed Test'");
 
 private By Go_Button = AppiumBy.accessibilityId("Go");
 
 //Home Icon
 private By HomeIcon = AppiumBy.accessibilityId("Home");
 
 
 
 public void clickHome() {
	 
	 clickElement(HomeIcon);
 }
 
 
//Split tunneling 
private By SplitTunnelingOn = AppiumBy.iOSClassChain("**/XCUIElementTypeSwitch[`value == \"0\"`][2]");

 

//Internal Kill switch 
private By killSwitch = AppiumBy.iOSClassChain(
	    "**/XCUIElementTypeSwitch[`value == \"0\"`][1]"
	);

// Enable Kill switch
private By EnableButton= AppiumBy.accessibilityId("Enable"); 

//Disable Kill switch
private By DisableButton= AppiumBy.accessibilityId("Disable"); 

private By DeleteButton = AppiumBy.accessibilityId("Delete");

private By settings_button = AppiumBy.accessibilityId("Settings"); 


public void ClickSpeedTest() {
	
	clickElement(SpeedTestButton);
	
}

public void ClickGoButton() {
	
	clickElement(Go_Button);
}

 
//Turn On kill switch

public void TurnOnKillSwitch() {
	
	clickConnectionSettings();
	
	clickElement(killSwitch);
	
	clickElement(EnableButton);
	
	clickElement(HomeIcon);
	

	
}

private By killswitchOff= AppiumBy.iOSClassChain("**/XCUIElementTypeSwitch[`value == '1'`][1]");

public void TurnOffKillSwitch() {
	
	clickConnectionSettings();
	
	clickElement(killswitchOff);
	
	clickElement(DisableButton);
	
	clickElement(HomeIcon);
 
	
}




 //Click on home icon
 
 public void clickHomeIcon() {
	 
	 clickElement(HomeIcon);
	 
 }
 
 
 //Click on connection settings
 public void clickConnectionSettings() {
	 clickElement(ConnectionSettings);
	 
 }


 
//Protocol  Locators and Selection

 private By VMess_Protocol = AppiumBy.accessibilityId("VMess, Fast and reliable for overcoming restrictions");
 private By WireGuard_Protocol = AppiumBy.accessibilityId("WireGuard, Strong balance between speed and security");

 public void clickVMess() {
	 clickElement(VMess_Protocol);
	 
 }
 
 public void clickWireGuard() {
	 clickElement(WireGuard_Protocol);
	 
 }
 
 
 
 
 
 
 public void click_Backbutton() {
	 
 clickElement(BackButton);
	 
 }
 
 
 

 

 public void gotoSettingspage() {
	 
	 clickElement(settings_button);
	 //clickAtCoordinates(36,64);
 }
 
 
 private By splitTunnelInput = AppiumBy.iOSNsPredicateString("value == 'Add domain to bypass…'");
 private By addUrl = AppiumBy.accessibilityId("Add");


 // Once split tunneling is enabled, the switch's value flips from "0" to
 // "1" - tapping it again (same position) reopens the split-tunnel
 // config screen to add another URL, without turning the feature off.
 private By SplitTunnelingOff = AppiumBy.iOSClassChain("**/XCUIElementTypeSwitch[`value == \"1\"`][2]");


 public void CreateSplitTunnelUrl(String url) {

	  clickElement(SplitTunnelingOn);
	  clickElement(EnableButton);
	  driver.findElement(splitTunnelInput).sendKeys(url);
	  clickElement(addUrl);
	
	 

 }
 
 public void turnoffSplitTunneling() {
	 clickElement(SplitTunnelingOff);
	 clickElement(DisableButton);
	 
 }

 


 
// Fetch the IP from the Settings 
 
 public void getIPEnova() 
{
	 

}






}
 
	
 
 

