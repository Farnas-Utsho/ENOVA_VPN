package farnasutsho.AppiumFramework.Serverlist;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;


public class Get_Server_List {

    private static final String BASE_URL =
            "https://server.getenova.com/";

    public String getToken() {

        Response response =
                given()
                    .baseUri(BASE_URL)
                    .header("Content-Type", "application/json")
                    .body("""
                        {
                            "name": "SharkVPN",
                            "api_key": "G8@vP9kLrM"
                        }
                        """)
                .when()
                    .post("/api/v1/auth/provider/login")
                .then()
                    .statusCode(201)
                    .extract()
                    .response();

        String token = response.jsonPath().getString("data.token");

        return token;
    }

    public void writeServerDataToJson(
            List<Map<String, String>> servers,
            String jsonFile) {

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {

            mapper.writeValue(
                    new File(jsonFile),
                    servers
            );

            System.out.println(
                    "Server list saved successfully: " + jsonFile
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getServerList(int protocolId, String jsonFile) {

        String token = getToken();

        Assert.assertNotNull(token);
        Assert.assertFalse(token.isEmpty());

        Response serverlist =
                given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + token)
                    .header("Connection", "keep-alive")
                    .queryParam("protocol_id", protocolId)
                    .queryParam("is_subscription", true)
                    .queryParam("is_timeValid", true)

                .when()
                    .get("/api/v1/client/by/location");

        Assert.assertEquals(
                serverlist.getStatusCode(),
                200,
                "Failed for protocol ID: " + protocolId
        );

        List<Map<String, String>> servers =
                extractServerData(serverlist);

        writeServerDataToJson(servers, jsonFile);

        System.out.println(
                "Protocol " + protocolId +
                " → " + servers.size() +
                " servers written to " + jsonFile
        );
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, String>> extractServerData(Response response) {

        List<Map<String, String>> serverDataList = new ArrayList<>();

        List<Map<String, Object>> countries =
                response.jsonPath().getList("data");

        for (Map<String, Object> country : countries) {

            String countryName =
                    (String) country.get("name");

            List<Map<String, Object>> vpnList =
                    (List<Map<String, Object>>) country.get("vpn_list");

            // Determine whether country has one or multiple servers
            String numberOfServers =
                    vpnList.size() == 1 ? "single" : "multiple";

            for (Map<String, Object> server : vpnList) {

                Map<String, String> serverData = new HashMap<>();

                serverData.put(
                        "country",
                        countryName
                );

                serverData.put(
                        "server",
                        (String) server.get("name")
                );

                serverData.put(
                        "ip",
                        (String) server.get("ip")
                );

                serverData.put(
                        "numberofservers",
                        numberOfServers
                );

                serverDataList.add(serverData);
            }
        }

        return serverDataList;
    }
}



