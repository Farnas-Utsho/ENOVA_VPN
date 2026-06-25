package farnasutsho.AppiumFramework.IOS;

public class ServerInfo {
	
	private String country;
    private String server;

    public ServerInfo(String country, String server) {
        this.country = country;
        this.server = server;
    }

    public String getCountry() {
        return country;
    }

    public String getServer() {
        return server;
    }

    @Override
    public String toString() {
        return country + " -> " + server;
    }

}
