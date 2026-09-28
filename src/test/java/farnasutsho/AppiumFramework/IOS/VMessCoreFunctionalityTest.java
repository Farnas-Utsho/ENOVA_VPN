package farnasutsho.AppiumFramework.IOS;

import farnasutsho.AppiumFramework.IOSBase.BaseCoreFunctionalityTest;

public class VMessCoreFunctionalityTest extends BaseCoreFunctionalityTest {

    @Override
    protected void selectProtocol() {
        settings.clickVMess();
    }

    @Override
    protected String getJsonFile() {
        return System.getProperty("user.dir")
                + "/src/test/java/farnasutsho/AppiumFramework/testData/vmessServerList.json";
    }
}
