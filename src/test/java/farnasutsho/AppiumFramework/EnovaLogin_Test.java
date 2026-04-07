package farnasutsho.AppiumFramework;

import org.testng.annotations.Test;

import farnasutsho.AppiumFramework.TestUtils.AndroidBaseTest;
import farnasutsho.AppiumFramework.android.LandingPage;

public class EnovaLogin_Test extends AndroidBaseTest{
	
	
	@Test
	public void GuestLoginTest() {
		
		LandingPage landpage = new LandingPage(driver);
		
		//landpage.guestLogIn();
		landpage.ClickLogin();
		
	}
	
	@Test
	public void LoginTest() {
		
		LandingPage landpage = new LandingPage(driver);
		
		//landpage.guestLogIn();
		landpage.ClickLogin();
		
	}
	
	
	

}
