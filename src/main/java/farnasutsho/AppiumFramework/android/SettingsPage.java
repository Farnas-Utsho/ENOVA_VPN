package farnasutsho.AppiumFramework.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import farnasutsho.AppiumFramework.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SettingsPage extends AndroidActions {

	AndroidDriver driver ;
	
	public SettingsPage(AndroidDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	@AndroidFindBy(accessibility="Connection settings")
	private WebElement ConnectionsettingsIcon;
	
	public void clickSettings() {
		
		ConnectionsettingsIcon.click();
		
	}
	
	@AndroidFindBy(xpath="//android.view.View[contains(@content-desc,'Internet kill switch')]")
	private WebElement killSwitch;
	
	public void clickKillSwitch() {
		
		killSwitch.click();
	}
	
	

	
	@AndroidFindBy(xpath="//android.view.View[contains(@content-desc,'Encryption protocol')]")
	private WebElement selectProtocol;
	
	public void clickProtocol() {
		
		selectProtocol.click();
	}
	
	
	@AndroidFindBy(xpath="//android.view.View[contains(@content-desc,'Split tunneling')]")
	private WebElement splitTunneling;
	
	public void clickSplitTunneling() {
		
		splitTunneling.click();
	}
	
	@AndroidFindBy(xpath="//android.widget.Switch[contains(@content-desc,'WireGuard')]")
	private WebElement wireguardProtocol ;
	
	
	public void ClickWireguard() {
		wireguardProtocol.click();
		
	}

}
