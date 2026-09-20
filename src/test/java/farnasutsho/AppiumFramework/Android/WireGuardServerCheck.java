package farnasutsho.AppiumFramework.Android;

import farnasutsho.AppiumFramework.AndroidBase.BaseServerStatusCheck;

public class WireGuardServerCheck extends BaseServerStatusCheck {

    @Override
    protected void selectProtocol() {
        settings.ClickWireguard();
    }

    @Override
    protected String getJsonFile() {
        return System.getProperty("user.dir")
                + "/src/test/java/farnasutsho/AppiumFramework/testData/serverlist.json";
    }
}
