package com.equipment.crudrestful;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.testng.Assert.assertEquals;

public class ClientActionsTests {

    WireMockServer wireMockServer;
    String equipmentNumber = "1";

    @BeforeTest
    public void setUp () {
        wireMockServer = new WireMockServer(wireMockConfig().port(8080));;
        wireMockServer.start();
    }

    @Test
    public void testCreateEquipment() throws IOException {

        String responseBodyGet = ClientActions.getEquipmentByNumber(equipmentNumber);
        Assert.assertTrue(responseBodyGet.contains("Equipment not found for this id :: " + equipmentNumber));

        int responseCodePost = ClientActions.createEquipment();
        assertEquals(responseCodePost, Integer.parseInt(equipmentNumber));

    }

    @Test
    public void testUpdateEquipment() {

//        String responseGet1 = ClientActions.getEquipmentByNumber();
//        assertEquals(responseGet1, "Error. This equipment has not been created ");
//
//        String responsePost = ClientActions.createEquipment();
//        assertTrue(responsePost.contains("equipmentNumber"));
//
//        String responseDelete = ClientActions.deleteEquipment();
//        assertEquals(responsePost, responseDelete);
//
//        String responseGet2 = ClientActions.getEquipmentByNumber();
//        assertEquals(responseGet2, "Error. This equipment has not been created ");

    }

    @Test
    public void testDeleteEquipmentByNumber() {

//        String responseGet1 = ClientActions.getEquipmentByNumber();
//        assertEquals(responseGet1, "Error. This equipment has not been created ");
//
//        String responsePost = ClientActions.createEquipment();
//        assertTrue(responsePost.contains("equipmentNumber"));
//
//        String responseDelete = ClientActions.deleteEquipment();
//        assertEquals(responsePost, responseDelete);
//
//        String responseGet2 = ClientActions.getEquipmentByNumber();
//        assertEquals(responseGet2, "Error. This equipment has not been created ");

    }

    @AfterTest
    public void tearDown () {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
