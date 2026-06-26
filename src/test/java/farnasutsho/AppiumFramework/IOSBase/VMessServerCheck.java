package farnasutsho.AppiumFramework.IOSBase;

import farnasutsho.AppiumFramework.IOS.ServerStatusCheck;

public class VMessServerCheck extends  ServerStatusCheck{

	
	   @Override
	    protected void selectProtocol() {
	        settings.clickVMess();
	    }

	    @Override
	    protected String getJsonFile() {
	        return System.getProperty("user.dir")
	                + "/src/test/java/farnasutsho/AppiumFramework/testData/serverlist.json";
	    }
	
}