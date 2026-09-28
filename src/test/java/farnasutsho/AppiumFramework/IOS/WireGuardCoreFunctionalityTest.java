package farnasutsho.AppiumFramework.IOS;

import farnasutsho.AppiumFramework.IOSBase.BaseCoreFunctionalityTest;

public class WireGuardCoreFunctionalityTest extends BaseCoreFunctionalityTest {

    @Override
    protected void selectProtocol() {
        settings.clickWireGuard();
    }

    @Override
    protected String getJsonFile() {
        return System.getProperty("user.dir")
                + "/src/test/java/farnasutsho/AppiumFramework/testData/wireGuardServerList.json";
    }
}
