package com.equipment.crudrestful;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;

import static com.equipment.crudrestful.config.ConfigWireMock.CREATE_EQUIPMENT_URL;
import static com.equipment.crudrestful.config.ConfigWireMock.GET_EQUIPMENT_BY_NUMBER_URL;

public class ClientActions {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static String getEquipmentByNumber(String equipmentNumber) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(GET_EQUIPMENT_BY_NUMBER_URL + equipmentNumber)
                .get()
                .build();

        return client.newCall(request).execute().body().string();
    }

    public static int createEquipment() throws IOException {

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, "{\n" +
                "    \"equipmentNumber\": 1,\n" +
                "    \"equipmentType\": \"UTR\",\n" +
                "    \"status\": \"disabled\",\n" +
                "    \"deviceId\": \"33\"\n" +
                "}");

        Request request = new Request.Builder()
                .url(CREATE_EQUIPMENT_URL)
                .post(requestBody)
                .build();

        return client.newCall(request).execute().code();

    }

    public static String updateEquipment() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8080/equipment/equipment/1")
                .get()
                .build();

        return client.newCall(request).execute().body().string();

    }

    public static int deleteEquipment() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8080/equipment/equipment/1")
                .delete()
                .build();

        return client.newCall(request).execute().code();

    }
}