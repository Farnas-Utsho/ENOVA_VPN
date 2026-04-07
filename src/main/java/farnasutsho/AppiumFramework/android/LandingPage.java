package farnasutsho.AppiumFramework.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import farnasutsho.AppiumFramework.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Hello world!
 */
public class LandingPage extends AndroidActions{
	
	AndroidDriver driver ;
	
	
	public LandingPage(AndroidDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
  @AndroidFindBy(accessibility="CONTINUE AS GUEST")	
  private WebElement guestButton ;
  
  public void guestLogIn() {
	  
	  guestButton.click();
	  
  }
  
  
  
  @AndroidFindBy(accessibility="LOGIN")
  private WebElement LogInButton;
  
  public void ClickLogin() {
	  
	  LogInButton.click();
	  
  }
  
  
  
}
