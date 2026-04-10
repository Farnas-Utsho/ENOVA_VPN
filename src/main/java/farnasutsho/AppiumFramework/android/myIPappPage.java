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
	

	
	
	@AndroidFindBy(id="com.ddm.iptools:id/text_ip")
	private WebElement getIP;
	
	public String getIpAddress() {
		
		System.out.println("This is only for testing");
		String ipAddress = getIP.getText();
		
		return ipAddress;
		
		
	}
	
	
	@AndroidFindBy(id="com.ddm.iptools:id/button_refresh")
	private WebElement refreshButton;
	
	public void clickRefreshButton() {
		
		refreshButton.click();
		
		
		
	}
	
	
	
	
	
	
}
