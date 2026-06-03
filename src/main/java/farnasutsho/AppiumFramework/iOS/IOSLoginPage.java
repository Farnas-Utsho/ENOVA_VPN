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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Hello world!
 */
public class IOSLoginPage extends IOSActions{
	
	
	IOSDriver driver ;
	public IOSLoginPage (IOSDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
		
	}

	
	
	
	
	
//Landing page 
	
private By 	SplashScreenLoginButton = AppiumBy.accessibilityId("LOGIN") ;
	
	

//Login page locators 
	
private By EmailInputField = AppiumBy.iOSNsPredicateString("label contains 'Email' OR type == 'XCUIElementTypeTextField' OR value Contains '@'");

private By PasswordInputField =
AppiumBy.iOSNsPredicateString(
	    "value Contains '•' or value Contains 'Password'"
	);

private By SingInButton = AppiumBy.accessibilityId("SIGN IN");

private By GoogleButton = AppiumBy.accessibilityId("Google");

private By AppleButton = AppiumBy.accessibilityId("APPLE");

private By GuestButton = AppiumBy.accessibilityId("CONTINUE AS GUEST");
 




 //Necessary functions 
public void InputEmail(String email) {

    WebElement emailField = driver.findElement(EmailInputField);
    emailField.click();
    emailField.clear();
    emailField.sendKeys(email);
    driver.hideKeyboard();
}

public void InputPassword(String password) {

    WebElement passField = driver.findElement(PasswordInputField);
  
    passField.click();
    passField.clear();
    passField.sendKeys(password);
    driver.hideKeyboard();
}

public void ClickSignIn() {
	
	clickElement(SingInButton);
}


public void ClickLoginButton() {
	// TODO Auto-generated method stub
	
}


public void clickLogIn() {
	clickElement(SplashScreenLoginButton);
	// TODO Auto-generated method stub
	
}




	
 
 
}
