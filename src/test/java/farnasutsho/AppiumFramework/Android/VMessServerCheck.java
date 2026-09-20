package farnasutsho.AppiumFramework.Android;

import farnasutsho.AppiumFramework.AndroidBase.BaseServerStatusCheck;

public class VMessServerCheck extends BaseServerStatusCheck {

    @Override
    protected void selectProtocol() {
        settings.ClickVMess();
    }

    @Override
    protected String getJsonFile() {
        return System.getProperty("user.dir")
                + "/src/test/java/farnasutsho/AppiumFramework/testData/serverlist.json";
    }
}
