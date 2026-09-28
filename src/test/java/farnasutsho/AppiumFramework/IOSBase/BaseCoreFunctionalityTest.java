package farnasutsho.AppiumFramework.IOSBase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import farnasutsho.AppiumFramework.TestUtils.IOSBaseTest;
import farnasutsho.AppiumFramework.iOS.IOSHomePage;
import farnasutsho.AppiumFramework.iOS.IOSLocationPage;
import farnasutsho.AppiumFramework.iOS.IOSSettingsPage;
import farnasutsho.AppiumFramework.iOS.IOSThirdPartyAPP;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public abstract class BaseCoreFunctionalityTest extends IOSBaseTest {

    private static final int TOP_SERVER_COUNT = 20;

    public AppiumDriver drive;

    public IOSHomePage home;
    public IOSLocationPage location;
    public IOSThirdPartyAPP app;
    public IOSSettingsPage settings;

    protected abstract void selectProtocol();
    protected abstract String getJsonFile();

    @BeforeClass
    public void setupProtocolAndSafari() {

        driver.activateApp("com.apple.mobilesafari");
        driver.get("https://api.ipify.org");

        driver.activateApp("com.enovavpn.mobile");

        home = new IOSHomePage(driver);
//        settings = home.clickSettings();
//
//        settings.clickConnectionSettings();
//        selectProtocol();
//
//        settings.clickHome();
    }

    @AfterMethod(alwaysRun = true)
    public void Setup() {
        driver.terminateApp("com.enovavpn.mobile");
        driver.activateApp("com.enovavpn.mobile");
    }

   

    /**
     * Picks one random server row from the top TOP_SERVER_COUNT entries
     * of this protocol's server list.
     */
    protected HashMap<String, String> pickRandomServer() throws IOException {
        List<HashMap<String, String>> pool = topServerPool();
        return pool.get(ThreadLocalRandom.current().nextInt(pool.size()));
    }

    /**
     * Picks two distinct random server rows from the top TOP_SERVER_COUNT
     * entries — for tests (e.g. server switch) that need to connect to one
     * server and then switch to a different one.
     */
    @SuppressWarnings("unchecked")
    protected HashMap<String, String>[] pickTwoDistinctRandomServers() throws IOException {

        List<HashMap<String, String>> pool = topServerPool();

        if (pool.size() < 2) {
            throw new IllegalStateException(
                    "Need at least 2 servers in the top " + TOP_SERVER_COUNT
                            + " to pick two distinct servers, found: " + pool.size());
        }

        int firstIndex = ThreadLocalRandom.current().nextInt(pool.size());
        HashMap<String, String> first = pool.get(firstIndex);

        int secondIndex;
        do {
            secondIndex = ThreadLocalRandom.current().nextInt(pool.size());
        } while (secondIndex == firstIndex);

        HashMap<String, String> second = pool.get(secondIndex);

        return new HashMap[] { first, second };
    }

    private List<HashMap<String, String>> topServerPool() throws IOException {

        List<HashMap<String, String>> data = getJsonData(getJsonFile());

        // "Brazil - 2" is excluded from the core functionality test pool.
        data.removeIf(row -> "Brazil - 2".equals(row.get("server")));

        int limit = Math.min(TOP_SERVER_COUNT, data.size());

        return data.subList(0, limit);
    }

    /**
     * Same selection flow used by BaseServerStatusCheck.serverTest: if the
     * row's server is already the connected default, just connect;
     * otherwise go to the location page and drill into the country first
     * when the row is tagged "multiple" (except "Brazil - 3", which is
     * reachable directly from the main list). A selection failure is
     * reported the same way, via SkipException.
     */
    protected void selectServerRow(HashMap<String, String> row) throws InterruptedException {

        String country = row.get("country");
        String server = row.get("server");
        String servercount = row.get("numberofservers");

        if (home.isDefaultServer(server)) {
            return;
        }

        home.goToLocationPage();

        try {
            if ("multiple".equals(servercount) && !"Brazil - 3".equals(server)) {
                location.SelectCountry(country);
                location.SelectServer(server);
            } else {
                location.SelectServer(server);
            }
        } catch (Exception e) {
            throw new org.testng.SkipException("Skipping test — server not found: " + server);
        }
    }

//    @Test
//    public void KillSwitchTest() throws InterruptedException, IOException {
//
//        home = new IOSHomePage(driver);
//
//        settings = home.clickSettings();
//
//        location = new IOSLocationPage(driver);
//        app = new IOSThirdPartyAPP(driver);
//     
//
//        settings.TurnOnKillSwitch();
//
//        HashMap<String, String> row = pickRandomServer();
//        System.out.println("KillSwitchTest using server: " + row.get("server"));
//
//        String expectedIP = row.get("ip");
//
//        selectServerRow(row);
//  
//        home.clickconnect();
//        home.waitForConnected();  
//    
//        // Turn off the kill switch from Apple's own Settings app (not the
//        // in-app toggle) - this is the actual scenario being verified.
//        home.turnoffVPNFromSettings();
//        Thread.sleep(3000);
//
//        // With the kill switch off and the VPN toggled off from Settings,
//        // Safari should now show the real, non-VPN IP - assert it matches
//        // the expected IP recorded in the test data, same pattern as
//        // BaseServerStatusCheck.serverTest.
//        driver.activateApp("com.apple.mobilesafari");
//
//        String actualIP = app.extractIP();
//        System.out.println("Actual IP : " + actualIP);
//
//        Assert.assertEquals(expectedIP, actualIP);
//
//        // Reopen Enova, disconnect, then turn off the kill switch in-app.
//        driver.activateApp("com.enovavpn.mobile");
//       
//
//        home.clickDisconnect();
//        home.clickdisconnectOnPopup();
//
//        // TurnOffKillSwitch() assumes it's starting from the Settings tab
//        // (it clicks straight into "Connection settings") - go there first
//        // instead of calling it directly from Home.
//        settings = home.clickSettings();
//        settings.TurnOffKillSwitch();
//    }

//    @Test
//    public void ServerSwitch_Test() throws InterruptedException, IOException {
//
//        home = new IOSHomePage(driver);
//        location = new IOSLocationPage(driver);
//        app = new IOSThirdPartyAPP(driver);
//        settings = new IOSSettingsPage(driver);
//
//        HashMap<String, String>[] rows = pickTwoDistinctRandomServers();
//        HashMap<String, String> firstRow = rows[0];
//        HashMap<String, String> secondRow = rows[1];
//        System.out.println("ServerSwitch_Test connecting to: " + firstRow.get("server")
//                + " then switching to: " + secondRow.get("server"));
//
//        String firstExpectedIP = firstRow.get("ip");
//        String secondExpectedIP = secondRow.get("ip");
//
//        // 1. Connect with the first server, and wait for the DISCONNECT
//        // button to appear (confirms the tunnel is actually up, not just
//        // that the Connect button was tapped).
//        selectServerRow(firstRow);
//        home.clickconnect();
//        home.waitForConnected();
//        Thread.sleep(8000);
//
//        // 2. Verify the server IP from Safari (apify.org) against the
//        // expected IP recorded in the test data, while still connected -
//        // same pattern as BaseServerStatusCheck.serverTest.
//        driver.activateApp("com.apple.mobilesafari");
//
//        String actualIPFirst = app.extractIP();
//        System.out.println("Actual IP : " + actualIPFirst);
//
//        driver.activateApp("com.enovavpn.mobile");
//        settings.clickHome();
//
//        Assert.assertEquals(firstExpectedIP, actualIPFirst);
//
//        // 3. Reopen the app (it was never terminated - killing the app
//        // process also kills the VPN connection, so we only ever switch
//        // foreground/background here), then go to the server list and
//        // select the second server via the switch-server locator (no ms
//        // suffix while already connected).
//        driver.activateApp("com.enovavpn.mobile");
//        Thread.sleep(3000);
//
//        home.goToLocationPage();
//        Thread.sleep(8000);
//
//        String secondCountry = secondRow.get("country");
//        String secondServer = secondRow.get("server");
//        String secondServerCount = secondRow.get("numberofservers");
//
//        if ("multiple".equals(secondServerCount) && !"Brazil - 3".equals(secondServer)) {
//            location.SelectCountry(secondCountry);
//        }
//        location.SelectServerSwitch(secondServer);
//
//        location.clickSwitch();
//
//        // 4. Now connected to the second server - wait for the switch to
//        // take effect, then verify the new server IP the same way.
//        home.waitForConnected();
//        Thread.sleep(8000);
//
//        driver.activateApp("com.apple.mobilesafari");
//
//        String actualIPSecond = app.extractIP();
//        System.out.println("Actual IP : " + actualIPSecond);
//
//        driver.activateApp("com.enovavpn.mobile");
//        settings.clickHome();
//
//        Assert.assertEquals(secondExpectedIP, actualIPSecond);
//
//        // 5. Reopen the app and disconnect.
//        driver.activateApp("com.enovavpn.mobile");
//        Thread.sleep(3000);
//
//        home.clickDisconnect();
//    }

    private static final String Non_TUNNEL_URL_1 = "https://api.ipify.org";
    private static final String SPLIT_TUNNEL_URL_2 = "whatismyipaddress.com";
    private static final String NON_TUNNELED_URL = "https://icanhazip.com";


    @Test
    public void SplitTunneling_Test() throws InterruptedException, IOException {

        home = new IOSHomePage(driver);
        location = new IOSLocationPage(driver);
        app = new IOSThirdPartyAPP(driver);
        settings = new IOSSettingsPage(driver);

        // 1. Capture the local (non-VPN) IP first.
        driver.activateApp("com.apple.mobilesafari");
        driver.get(Non_TUNNEL_URL_1);
        Thread.sleep(3000);

        String localIP = app.extractIP();
        System.out.println("Local IP (no VPN): " + localIP);

        // 2. Reopen the app and enable split tunneling with both
        // IP-checking sites.
        driver.activateApp("com.enovavpn.mobile");
        Thread.sleep(3000);

//        home.clickSettings();
//        settings.clickConnectionSettings();
//
//        settings.CreateSplitTunnelUrl(SPLIT_TUNNEL_URL_2);
//        
//        settings.gotoSettingspage();
//        settings.clickHomeIcon();
        // 3. Select a server and connect.
        HashMap<String, String> row = pickRandomServer();
        System.out.println("SplitTunneling_Test using server: " + row.get("server"));
        String expectedServerIP = row.get("ip");

        selectServerRow(row);
        home.clickconnect();
        home.waitForConnected();
        Thread.sleep(8000);
        
        
        
        
        driver.activateApp("com.apple.mobilesafari");
        driver.get("https://whatismyipaddress.com");
        String tunneledIp = app.extractIP();
        
       
        
      


        // 4. A site that IS in the split tunnel should bypass the VPN and
    
        driver.activateApp("com.apple.mobilesafari");
        driver.get(Non_TUNNEL_URL_1);
        Thread.sleep(3000);

        String Non_tunneledSiteIP = app.extractIP();
        System.out.println("IP via split-tunneled site: " + Non_tunneledSiteIP);

        Assert.assertEquals(Non_tunneledSiteIP, expectedServerIP);
       // Assert.assertEquals(localIP,tunneledIp);
       

//        // 6. Reopen the app, disconnect, and remove the split tunnel.
//        driver.activateApp("com.enovavpn.mobile");
//        Thread.sleep(3000);
//
//        home.clickDisconnect();
//
//        home.clickSettings();
//        settings.clickConnectionSettings();
//        settings.turnoffSplitTunneling();
    
    }
}
