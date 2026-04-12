package farnasutsho.AppiumFramework.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import farnasutsho.AppiumFramework.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class myIPappPage extends AndroidActions{

	AndroidDriver driver ;
	
	
	public myIPappPage(AndroidDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	

	
	
	@AndroidFindBy(id="cz.webprovider.whatismyipaddress:id/zobraz_ip")
	private WebElement getIP;
	
	public String getIpAddress() {
		
	
		String ipAddress = getIP.getText();
		System.out.println(ipAddress);
		
		return ipAddress;
		
		
	}
	
	
	@AndroidFindBy(id="cz.webprovider.whatismyipaddress:id/refresh_info")
	private WebElement refreshButton;
	
	public void clickRefreshButton() {
		
		clickElement(refreshButton);
		
		
		
	}
	
	
	
	
	
	
}
