package farnasutsho.AppiumFramework.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;


public class AppiumUtils {
// all the common  utills for the android and IOS will be listed here . here methods will be available for cross platform
	AppiumDriver driver;
	AppiumDriverLocalService service;

	
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress , int port) {
		
		//Start appium server programmatically
		service = new AppiumServiceBuilder().withAppiumJS(new File("//Users//farnasutsho//.nvm//versions//node//v24.14.1//lib//node_modules//appium//build//lib//main.js"))
				
				.withIPAddress(ipAddress)
				.usingPort(port).build();
		
		service.start();
		return service ;
		
		
	}
	
	
	public String getScreenShot(String testCaseName , AppiumDriver driver) throws IOException {
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile =System.getProperty("user.dir")+"//reports"+testCaseName+".png";
		
		FileUtils.copyFile(source, new File( destinationFile));
		return destinationFile;
		
	}
	
	public void Wait() {
		
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
				
	}
	

}
