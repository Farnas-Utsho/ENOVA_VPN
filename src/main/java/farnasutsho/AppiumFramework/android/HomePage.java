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
	private WebElement connectButton;

	public void clickConnect() {
	    connectButton.click();
	}
	
	
	@AndroidFindBy(xpath="//android.view.View[contains(@content-desc,'Auto')]")
	private WebElement serverNameButton;
	
	public void GoToServerList() throws InterruptedException {
		
		Thread.sleep(5000);
		serverNameButton.click();
	}
	
	
	
	
	
}
