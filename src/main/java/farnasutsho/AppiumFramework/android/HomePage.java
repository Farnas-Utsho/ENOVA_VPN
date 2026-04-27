package farnasutsho.AppiumFramework.android;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import farnasutsho.AppiumFramework.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends AndroidActions{

	AndroidDriver driver ;
	
	
	public HomePage(AndroidDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	

	private By connectdiscoButton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)");
	
	

	
	
	
	public void clickConnect() {

		clickElement(connectdiscoButton);

		

	}

	public void clickDisConnect() {
		clickElement(connectdiscoButton);
	}
	
	
	
	
	
	
	
	
	

	
	

	

	
	private By closeButton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(0)");
	

	public void connectionReportPopClose() {
		clickElement(closeButton);
	}
	
	
	
	private By serverNameButton = AppiumBy.xpath("//android.view.View[contains(@content-desc,'Auto')]");
	
	
	
	public void GoToServerList() throws InterruptedException {

		clickElement(serverNameButton);
	}
	
	
	
	
	@AndroidFindBy(xpath="//android.view.View[contains(@content-desc,'Connected') and contains(@content-desc,'Downloaded')]")
	private WebElement serverInformation;
	
	public String VPNiPAddress() {

	    String fullText = serverInformation.getAttribute("contentDescription");

	   
	    // Extract IP using regex
	    Pattern pattern = Pattern.compile("\\b\\d{1,3}(?:\\.\\d{1,3}){3}\\b");
	    Matcher matcher = pattern.matcher(fullText);

	    if (matcher.find()) {
	        return matcher.group(); // returns the IP
	    } else {
	        return null; // or throw exception if you prefer
	    }
	}
	
	
	
	
	private By DisconnectOnpopup = AppiumBy.accessibilityId("DISCONNECT");
	
	
	
	
	public void ClickDisconnectOnPopUp() {
		// TODO Auto-generated method stub
		clickElement( DisconnectOnpopup);}
		
	
	private By settingsIcon  = AppiumBy.accessibilityId("Settings");
	
	public SettingsPage clickSettings() {
		
		clickElement(settingsIcon);
		
		return new SettingsPage(driver);
		
	}
	
	

	
	
	
	
	
	
	
}
