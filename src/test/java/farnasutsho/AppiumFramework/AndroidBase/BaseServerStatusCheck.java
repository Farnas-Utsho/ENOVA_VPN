package farnasutsho.AppiumFramework.AndroidBase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import farnasutsho.AppiumFramework.TestUtils.AndroidBaseTest;
import farnasutsho.AppiumFramework.android.HomePage;
import farnasutsho.AppiumFramework.android.LocationPage;
import farnasutsho.AppiumFramework.android.SettingsPage;
import farnasutsho.AppiumFramework.android.myIPappPage;

public abstract class BaseServerStatusCheck extends AndroidBaseTest {

    protected HomePage home;
    protected LocationPage location;
    protected SettingsPage settings;
    protected myIPappPage iptest;

    protected abstract void selectProtocol();
    protected abstract String getJsonFile();

    @BeforeClass
    public void setupProtocol() {

        home = new HomePage(driver);
        settings = home.clickSettings();

        settings.clickProtocol();
        selectProtocol();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp(ITestResult result) {

        if (!result.isSuccess()) {
            driver.terminateApp("com.enovavpn.mobile");
            driver.activateApp("com.enovavpn.mobile");
        }
    }

    @Test(dataProvider = "getData")
    public void serverTest(HashMap<String, String> input) throws InterruptedException {

        home = new HomePage(driver);
        location = new LocationPage(driver);
        settings = new SettingsPage(driver);
        iptest = new myIPappPage(driver);

        String country = input.get("country");
        String server = input.get("Server");

        home.GoToServerList();

        if (country != null && !country.trim().isEmpty()) {
            System.out.println("Selecting country: " + country);
            location.SelectCountry(country);
        } else {
            System.out.println("No country provided, skipping country selection");
        }

        System.out.println("Selecting server : " + server);

        try {
            location.SelectServer(server);
        } catch (Exception e) {
            throw new SkipException("Skipping test — server not found: " + server);
        }

        home.clickConnect();
        humanPause();

        driver.activateApp("cz.webprovider.whatismyipaddress");
        humanPause();

        String actualIP = iptest.getIpAddress();
        System.out.println("Ip from the third party app: " + actualIP);

        driver.activateApp("com.enovavpn.mobile");
        humanPause();

        String expected_ip = home.VPNiPAddress();
        System.out.println("IP from vpn applicatoin : " + expected_ip);

        Assert.assertEquals(actualIP, expected_ip,
                "IP mismatch! VPN IP and Third-party IP are not the same.");

        home.clickDisConnect();
        home.ClickDisconnectOnPopUp();
        home.connectionReportPopClose();
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonData(getJsonFile());

        Object[][] arr = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            arr[i][0] = data.get(i);
        }

        return arr;
    }
}
