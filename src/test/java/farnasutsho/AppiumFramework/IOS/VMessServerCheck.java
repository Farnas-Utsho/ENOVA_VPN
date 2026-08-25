package farnasutsho.AppiumFramework.IOS;

import farnasutsho.AppiumFramework.IOSBase.BaseServerStatusCheck;

public class VMessServerCheck extends BaseServerStatusCheck {

    @Override
    protected void selectProtocol() {
        settings.clickVMess();
    }

    @Override 
    protected int getProtocolId() {
        return 3;
    }

    @Override
    protected boolean isWireGuard() {
        return false;
    }
    @Override
    protected String getJsonFile() {
        return System.getProperty("user.dir")
                + "/src/test/java/farnasutsho/AppiumFramework/testData/serverlist.json";
    }
}