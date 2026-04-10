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
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[3]")
	private WebElement connectdiscoButton;

	public void clickConnect() {
		connectdiscoButton.click();
	}
	
	public void clickDisConnect() {
		connectdiscoButton.click();
	}
	
	
	

	
	
	@AndroidFindBy(accessibility="DISCONNECT")
	private WebElement disconnectButton;
	
	public void ClickDisconnectOnPopUp() {
		
		disconnectButton.click();
		
	}
	
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[1]")
	private WebElement closeButton;

	public void connectionReportPopClose() {
		closeButton.click();
	}
	
	
	
	
	@AndroidFindBy(xpath="//android.view.View[contains(@content-desc,'Auto')]")
	private WebElement serverNameButton;
	
	public void GoToServerList() throws InterruptedException {
		
		Thread.sleep(5000);
		serverNameButton.click();
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
	
	
	
	
	
	
	
	
}
