package farnasutsho.AppiumFramework.IOS;

import farnasutsho.AppiumFramework.IOSBase.BaseServerStatusCheck;

public class WireGuardServerCheck extends BaseServerStatusCheck {

    @Override
    protected void selectProtocol() {
        settings.clickWireGuard();
    }

    @Override
    protected int getProtocolId() {
        return 5;
    }

    @Override
    protected String getJsonFile() {
        return System.getProperty("user.dir")
                + "/src/test/java/farnasutsho/AppiumFramework/testData/serverlist_wireguard.json";
    }
}