package mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.squareup.okhttp.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static mock.config.ConfigWireMock.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ClientActionsTests {

    WireMockServer wireMockServer;
    String equipmentNumber = "1";

    @BeforeTest
    public void setUp() {
        wireMockServer = new WireMockServer(wireMockConfig().port(portForUrl));
        wireMockServer.start();
        configureStubs();
    }

    @Test
    public void testCreateEquipment() throws IOException {

        Response responseGet = ClientActions.getEquipmentByNumber(equipmentNumber);
        String responseBodyGet = responseGet.body().string();
        assertTrue(responseBodyGet.contains("Not Found"), "Response body should contain 'Not Found', but contains '" +
                responseBodyGet + "'");
        assertEquals(responseGet.code(), 404);

        Response responsePost = ClientActions.createEquipment();
        String responseBodyPost = responsePost.body().string();
        assertTrue(responseBodyPost.contains("equipmentNumber"));
        assertEquals(responsePost.code(), 201);

        Response responseDelete = ClientActions.deleteEquipment(equipmentNumber);
        assertEquals(responseDelete.code(), 200);
        wireMockServer.resetScenarios();

    }

    @Test
    public void testUpdateEquipment() throws IOException {

        Response responseGet = ClientActions.getEquipmentByNumber(equipmentNumber);
        String responseBodyGet = responseGet.body().string();
        assertTrue(responseBodyGet.contains("Not Found"), "Response body should contain 'Not Found', but contains '" +
                responseBodyGet + "'");
        assertEquals(responseGet.code(), 404);

        Response responsePost = ClientActions.createEquipment();
        String responseBodyPost = responsePost.body().string();
        assertTrue(responseBodyPost.contains("equipmentNumber"));
        assertEquals(responsePost.code(), 201);

        Response responseUpdate = ClientActions.updateEquipment(equipmentNumber);
        String responseBodyUpdate = responseUpdate.body().string();
        assertTrue(responseBodyUpdate.contains("enabled"), "Response body should contain 'enabled', but contains '" +
                responseBodyUpdate + "'");
        assertEquals(responseUpdate.code(), 200);

        Response responseDelete = ClientActions.deleteEquipment(equipmentNumber);
        assertEquals(responseDelete.code(), 200);
        wireMockServer.resetScenarios();

    }

    @Test
    public void testDeleteEquipmentByNumber() throws IOException {

        Response responseGet = ClientActions.getEquipmentByNumber(equipmentNumber);
        String responseBodyGet = responseGet.body().string();
        assertTrue(responseBodyGet.contains("Not Found"), "Response body should contain 'Not Found', but contains '" +
                responseBodyGet + "'");
        assertEquals(responseGet.code(), 404);

        Response responsePost = ClientActions.createEquipment();
        String responseBodyPost = responsePost.body().string();
        assertTrue(responseBodyPost.contains("equipmentNumber"));
        assertEquals(responsePost.code(), 201);

        Response responseDelete = ClientActions.deleteEquipment(equipmentNumber);
        assertEquals(responseDelete.code(), 200);
        wireMockServer.resetScenarios();

    }

    @AfterTest
    public void tearDown() {

        if (wireMockServer != null) {
            wireMockServer.stop();
        }

    }
}
