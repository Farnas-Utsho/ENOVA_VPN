package farnasutsho.AppiumFramework.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import farnasutsho.AppiumFramework.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LocationPage extends AndroidActions{

	AndroidDriver driver ;
	
	
	public LocationPage(AndroidDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	

	
	
	@AndroidFindBy(xpath="//android.view.View[contains(@content-desc,'Auto')]")
	private WebElement serverNameButton;
	
	public void GoToServerList() {
		serverNameButton.click();
	}
	

	public void SelectCountry(String countryName) {
		
		getCountryByName(countryName).click();
		
	}
	
	public void SelectServer(String serverName) {
		
		selectServer(serverName).click();
	}
	
	
}
