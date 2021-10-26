package mock;

import com.squareup.okhttp.*;

import java.io.IOException;

import static mock.config.ConfigWireMock.*;

public class ClientActions {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static Response getEquipmentByNumber(String equipmentNumber) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(GET_EQUIPMENT_BY_NUMBER_URL_WITH_PORT + equipmentNumber)
                .header("Content-Type","application/json")
                .get()
                .build();

        return client.newCall(request).execute();

    }

    public static Response createEquipment() throws IOException {

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, jsonBody);

        Request request = new Request.Builder()
                .url(CREATE_EQUIPMENT_URL_WITH_PORT)
                .header("Content-Type","application/json")
                .post(requestBody)
                .build();

        return client.newCall(request).execute();

    }

    public static Response updateEquipment(String equipmentNumber) throws IOException {

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBodyPut = RequestBody.create(JSON, jsonBodyChanged);
        Request request = new Request.Builder()
                .url(EDIT_EQUIPMENT_TYPE_URL_WITH_PORT + equipmentNumber)
                .header("Content-Type","application/json")
                .put(requestBodyPut)
                .build();

        return client.newCall(request).execute();

    }

    public static Response deleteEquipment(String equipmentNumber) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(DELETE_EQUIPMENT_URL_WITH_PORT + equipmentNumber)
                .header("Content-Type","application/json")
                .delete()
                .build();

        return client.newCall(request).execute();

    }
}