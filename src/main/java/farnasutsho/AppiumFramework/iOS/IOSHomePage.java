package farnasutsho.AppiumFramework.iOS;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;


import farnasutsho.AppiumFramework.utils.IOSActions;

import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Hello world!
 */
public class IOSHomePage extends IOSActions{
	
	IOSDriver driver ;
	public IOSHomePage(IOSDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
		
	}


	
	

	
  @iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeImage[`name == \"Connect\"`]")	
  private WebElement ConnectButton ;
  
  public void ClickConnect() {
	  
	  ConnectButton.click();
	  
  }
  
  
  @iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeImage[`name == \"Connected\"`]")
  private WebElement DisconnectionButton;
  
  public void ClickDisconnect() {
	  
	  DisconnectionButton.click();
	  
  }
  
  
  @iOSXCUITFindBy(iOSNsPredicate =
		    "name CONTAINS 'server available'"
		)
		private WebElement serverList;
  
  
  public void GoToServerList() {
	  
	  serverList.click();
  }
  
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'MB'")
  private List<WebElement> mbElements;
  

  public String extractIP() {

	    Pattern pattern = Pattern.compile("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");

	    for (WebElement el : mbElements) {

	        String text = el.getText();

	        if (text == null) continue;

	        Matcher matcher = pattern.matcher(text);

	        if (matcher.find()) {
	            return matcher.group();
	        }
	    }

	    throw new RuntimeException("IP not found");
	}
  
  
  
}
