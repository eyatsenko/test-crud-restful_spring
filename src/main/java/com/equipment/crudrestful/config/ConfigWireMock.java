package com.equipment.crudrestful.config;

import org.eclipse.jetty.client.api.Result;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ConfigWireMock {

    private static final String GET_EQUIPMENT_BY_NUMBER_URL = "/equipment/equipment/1";
    private static final String CREATE_EQUIPMENT_URL = "/equipment/equipment";
    private static final String EDIT_EQUIPMENT_TYPE_URL = "/equipment/equipment/1";
    private static final String DELETE_EQUIPMENT_URL = "/equipment/equipment/1";

    public static Result stubForGet () {
        stubFor(post(GET_EQUIPMENT_BY_NUMBER_URL)
                .withHeader("Content-Type", containing("application/json"))
                .withRequestBody(equalTo("{\n" +
                        "    \"equipmentType\":\"UTR\",\n" +
                        "    \"status\": \"disabled\",\n" +
                        "    \"deviceId\": \"33\"\n" +
                        "}"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody("<response>SUCCESS</response>")));

        Result result = null;
        return result;
    }

    public static Result stubForCreate () {
        stubFor(post(CREATE_EQUIPMENT_URL)
                .withHeader("Content-Type", containing("application/json"))
                .withRequestBody(equalTo("{\n" +
                        "    \"equipmentType\":\"UTR\",\n" +
                        "    \"status\": \"disabled\",\n" +
                        "    \"deviceId\": \"33\"\n" +
                        "}"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody("<response>SUCCESS</response>")));

        Result result = null;
        return result;
    }

    public static Result stubForEdit () {
        stubFor(put(EDIT_EQUIPMENT_TYPE_URL)
                .withHeader("Content-Type", containing("application/json"))
                .withRequestBody(equalTo("{\n" +
                        "    \"equipmentType\":\"TTT\",\n" +
                        "    \"status\": \"enabled\",\n" +
                        "    \"deviceId\": \"44\"\n" +
                        "}"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody("<response>SUCCESS</response>")));

        Result result = null;
        return result;
    }

    public static Result stubForDelete () {
        stubFor(delete(DELETE_EQUIPMENT_URL)
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody("<response>SUCCESS</response>")));

        Result result = null;
        return result;
    }
}