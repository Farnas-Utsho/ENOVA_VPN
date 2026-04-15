package farnasutsho.AppiumFramework.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import farnasutsho.AppiumFramework.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class youtube extends AndroidActions{

	AndroidDriver driver ;
	
	
	public youtube(AndroidDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	

	
	
	@AndroidFindBy(accessibility="Search")
	private WebElement searchIcon;
	
	public void SearchVideo() {
		
		searchIcon.click();
		
	}
	
	
	
	
	@AndroidFindBy(id="com.google.android.youtube:id/search_edit_text")
	private WebElement searchInputField;
	
	public void inputSerachItem() {
		
		searchInputField.sendKeys("2 min nature video");
			
		
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id=\"com.google.android.youtube:id/text\" and @text=\"2 minute nature video\"]")
	private WebElement Search;
	
	public void searchVideo() 
	
	{
		Search.click();
		
	}
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id=\"com.google.android.youtube:id/results\"]/android.view.ViewGroup[4]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]")
	private WebElement Targetvideo;
	
	public void clickVideo() {
		
		Targetvideo.click();
		
	}
	
	
	
	
	
	
	
	
}
