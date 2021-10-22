package com.equipment.crudrestful;

import com.equipment.crudrestful.config.ConfigWireMock;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import wiremock.org.eclipse.jetty.client.api.Result;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ClientActionsTests {

    WireMockServer wireMockServer;

    @BeforeTest
    public void setUp () {
        wireMockServer = new WireMockServer(wireMockConfig().port(8080));;
        wireMockServer.start();
    }


    @Test
    public void testGetEquipmentByNumber() {

        stubFor(post("/equipment/equipment")
                .withHeader("Content-Type", containing("application/json"))
                .withRequestBody(equalTo("{\n" +
                        "    \"equipmentType\":\"UTR\",\n" +
                        "    \"status\": \"disabled\",\n" +
                        "    \"deviceId\": \"33\"\n" +
                        "}"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody("<response>SUCCESS</response>")));

        ConfigWireMock configWireMock = new ConfigWireMock();

        Result result = configWireMock.stubForCreate();
//        assertTrue(result.wasSuccessful());

//        verify(postRequestedFor(urlPathEqualTo("/my/resource"))
//                .withRequestBody(matching(".*message-1234.*"))
//                .withHeader("Content-Type", equalTo("text/xml")));
//
//        WireMock.reset();
    }

    @Test
    public void testCreateEquipment() throws IOException {


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8081/get/user/1")
                .get()
                .build();

        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        System.out.println(responseBody);

        String expectedBody = "Here it is!";


        Assert.assertTrue(responseBody.contains(expectedBody), "Body should contain '" + expectedBody + "', but contains '" +
                responseBody + "' !!!");

    }

    @Test
    public void testUpdateEquipment() {

        String responseGet1 = ClientActions.getEquipmentByNumber();
        assertEquals(responseGet1, "Error. This equipment has not been created ");

        String responsePost = ClientActions.createEquipment();
        assertTrue(responsePost.contains("equipmentNumber"));

        String responseDelete = ClientActions.deleteEquipment();
        assertEquals(responsePost, responseDelete);

        String responseGet2 = ClientActions.getEquipmentByNumber();
        assertEquals(responseGet2, "Error. This equipment has not been created ");

    }

    @Test
    public void testDeleteEquipmentByNumber() {

        String responseGet1 = ClientActions.getEquipmentByNumber();
        assertEquals(responseGet1, "Error. This equipment has not been created ");

        String responsePost = ClientActions.createEquipment();
        assertTrue(responsePost.contains("equipmentNumber"));

        String responseDelete = ClientActions.deleteEquipment();
        assertEquals(responsePost, responseDelete);

        String responseGet2 = ClientActions.getEquipmentByNumber();
        assertEquals(responseGet2, "Error. This equipment has not been created ");

    }

    @AfterTest
    public void tearDown () {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
