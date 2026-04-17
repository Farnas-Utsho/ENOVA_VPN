package farnasutsho.AppiumFramework.android;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import farnasutsho.AppiumFramework.utils.AndroidActions;
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
	
	
	//Shark Connection button
	@AndroidFindBy(accessibility ="Connected")
	private WebElement connectdiscoButton;

	public void clickConnect() {
<<<<<<< HEAD
		clickElement(connectdiscoButton);
=======
		
		connectdiscoButton.click();
		
>>>>>>> 05a332220c95ee5e4e787c0fc8b7e3665884913b
	}
	
	public void clickDisConnect() {
		clickElement(connectdiscoButton);
	}
	
	
	

	
	
	@AndroidFindBy(accessibility="DISCONNECT")
	private WebElement disconnectButton;
	
	public void ClickDisconnectOnPopUp() {

	  

		clickElement( disconnectButton);
	}
	
	
	
	
	//Go to location page
	@AndroidFindBy(xpath="   ")
	
	
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[1]")
	private WebElement closeButton;

	public void connectionReportPopClose() {
		clickElement(closeButton);
	}
	
	
	
	
	@AndroidFindBy(xpath="//android.view.View[contains(@content-desc,'Auto')]")
	private WebElement serverNameButton;
	
	public void GoToServerList() throws InterruptedException {
		
<<<<<<< HEAD
		
=======
		Thread.sleep(5000);
>>>>>>> 05a332220c95ee5e4e787c0fc8b7e3665884913b
		clickElement(serverNameButton);
	}
	
	
	@AndroidFindBy(xpath="//android.view.View[contains(@content-desc,'Connected')]")
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
	
	
	@AndroidFindBy(accessibility="Settings")
	private WebElement settingsIcon;
	
	public SettingsPage clickSettings() {
		
		clickElement(settingsIcon);
		
		return new SettingsPage(driver);
		
	}
	
	
	
	
	
	
	
}
