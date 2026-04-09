package farnasutsho.AppiumFramework.android;

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
	
	
	
	
	
	
	
	
}
