package farnasutsho.AppiumFramework.iOS;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import farnasutsho.AppiumFramework.utils.IOSActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Hello world!
 */
public class IOSThirdPartyAPP extends IOSActions{
	
	
	
	
	IOSDriver driver ;
	public IOSThirdPartyAPP(IOSDriver driver) {
		super(driver);
	
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); 
		// TODO Auto-generated constructor stub
	}





	
	
// Related locators //

	
	private By ipAddress =
		    AppiumBy.iOSNsPredicateString("name MATCHES '[0-9]{1,3}(\\.[0-9]{1,3}){3}'");
	


	private By refreshButton = AppiumBy.accessibilityId("Refresh");







//Related functions //



  
  
public void clickRefreshButton() {
	
	clickElement(refreshButton);
}

  
  
  
  
  
public String extractIP() {
	
	
	clickRefreshButton();

    
    try {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Pattern pattern = Pattern.compile("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");

    	
        // wait until ANY element contains a valid IP
        Boolean ipFound = wait.until(driver -> {
            List<WebElement> elements = driver.findElements(ipAddress);

            for (WebElement el : elements) {
                String text = el.getText();
                if (text == null) continue;

                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    return true;
                }
            }
            return false;
        });

        if (!ipFound) {
            return null;
        }

        // extract again after wait succeeds
        List<WebElement> elements = driver.findElements(ipAddress);

        for (WebElement el : elements) {
            String text = el.getText();
            if (text == null) continue;

            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                return matcher.group();
            }
        }

        return null;

    } catch (TimeoutException e) {
        System.out.println("IP not found within timeout");
        return null;
    }
}
  
  
  
}
