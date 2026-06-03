package farnasutsho.AppiumFramework.android;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import farnasutsho.AppiumFramework.utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LocationPage extends AndroidActions{

	AndroidDriver driver ;
	
	
	public LocationPage(AndroidDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	

	public void clickElementLocation(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	private By serverNameButton  = AppiumBy.xpath("//android.view.View[contains(@content-desc,'Auto')]");
	
	public void GoToServerList() {
		clickElement(serverNameButton);
	}
	
	
	

public void SelectCountry(String countryName) {
		
	clickElementLocation(getCountryByName(countryName));
		
	}


	
public void SelectServer(String serverName) {
		
		clickElementLocation(selectServer(serverName));
	}
	
	

	private By clickSwitch  = AppiumBy.accessibilityId("Switch");
	
	public void ClickSwitchServer() {
		clickElement(clickSwitch);
		
	}
	
	
	
}
