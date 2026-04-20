package farnasutsho.AppiumFramework.android;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import farnasutsho.AppiumFramework.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
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

	    clickRefreshButton();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	    wait.until(driver -> {
	        String text = getIP.getText();
	        return text != null && !text.trim().isEmpty();
	    });

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
