package farnasutsho.AppiumFramework.iOS;

import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import farnasutsho.AppiumFramework.utils.AndroidActions;
import farnasutsho.AppiumFramework.utils.IOSActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Hello world!
 */
public class iOSPages extends IOSActions{
	
	IOSDriver driver ;
	
	

	
  @iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name == \"Steppers\"`]")	
  private WebElement exampleField ;
  
  public void ClickFemale() {
	  
	  exampleField.sendKeys("Dummy");
	  
  }
  
  
}
