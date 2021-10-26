package mock.config;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;

public class ConfigWireMock {

    public static final int port8080 = 8080;
    public static final int port8744 = 8744;
    public static int portForUrl = port8080;
    public static final String GET_EQUIPMENT_BY_NUMBER_URL = "/equipment/equipment/";
    public static final String CREATE_EQUIPMENT_URL = "/equipment/equipment";
    public static final String EDIT_EQUIPMENT_TYPE_URL = "/equipment/equipment/";
    public static final String DELETE_EQUIPMENT_URL = "/equipment/equipment/";
    public static final String GET_EQUIPMENT_BY_NUMBER_URL_WITH_PORT = "http://localhost:" + portForUrl + GET_EQUIPMENT_BY_NUMBER_URL;
    public static final String CREATE_EQUIPMENT_URL_WITH_PORT = "http://localhost:" + portForUrl + CREATE_EQUIPMENT_URL;
    public static final String EDIT_EQUIPMENT_TYPE_URL_WITH_PORT = "http://localhost:" + portForUrl + EDIT_EQUIPMENT_TYPE_URL;
    public static final String DELETE_EQUIPMENT_URL_WITH_PORT = "http://localhost:" + portForUrl + DELETE_EQUIPMENT_URL;

    public static final String jsonBody = "{\n" +
            "    \"equipmentNumber\": 1,\n" +
            "    \"equipmentType\":\"UTR\",\n" +
            "    \"status\": \"disabled\",\n" +
            "    \"deviceId\": \"33\"\n" +
            "}";
    public static final String jsonBodyChanged = "{\n" +
            "    \"equipmentNumber\": 1,\n" +
            "    \"equipmentType\":\"TTT\",\n" +
            "    \"status\": \"enabled\",\n" +
            "    \"deviceId\": \"44\"\n" +
            "}";


    public static void configureStubs() {
        configureFor(portForUrl);
        stubForGet();
        stubForCreate();
        stubForEdit();
        stubForDelete();
    }


    public static void stubForGet() {

        stubFor(get(GET_EQUIPMENT_BY_NUMBER_URL + 1)
                .inScenario("Equipment Creating")
                .whenScenarioStateIs(STARTED)
                .withHeader("Content-Type", containing("application/json"))
                .willSetStateTo("First Get")
                .willReturn(notFound().withBody("Not Found")));

        stubFor(get(GET_EQUIPMENT_BY_NUMBER_URL + 1).inScenario("Equipment Creating")
                .inScenario("Equipment Creating")
                .whenScenarioStateIs("First Get")
                .withHeader("Content-Type", containing("application/json"))
                .willSetStateTo("After Creating")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonBody)));

        stubFor(get(GET_EQUIPMENT_BY_NUMBER_URL + 1).inScenario("Equipment Creating")
                .inScenario("Equipment Creating")
                .whenScenarioStateIs("After Creating")
                .withHeader("Content-Type", containing("application/json"))
                .willSetStateTo("After Deleting")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)));

        stubFor(get(GET_EQUIPMENT_BY_NUMBER_URL + 1).inScenario("Equipment Creating")
                .inScenario("Equipment Creating")
                .whenScenarioStateIs("After Deleting")
                .withHeader("Content-Type", containing("application/json"))
                .willSetStateTo(STARTED)
                .willReturn(notFound().withBody("Not Found")));
    }

    public static void stubForCreate() {

        stubFor(post(CREATE_EQUIPMENT_URL)
                .withHeader("Content-Type", containing("application/json"))
                .withRequestBody(equalTo(jsonBody))
                .willReturn(created()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonBody)));
    }

    public static void stubForEdit() {
        stubFor(put(EDIT_EQUIPMENT_TYPE_URL + 1)
                .withHeader("Content-Type", containing("application/json"))
                .withRequestBody(equalTo(jsonBodyChanged))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonBodyChanged)));
    }

    public static void stubForDelete() {
        stubFor(delete(DELETE_EQUIPMENT_URL + 1)
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonBodyChanged)));
    }
}