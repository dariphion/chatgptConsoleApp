package org.example;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestBuilder {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public Request buildMessageRequest(String apiKey, String message, String apiUrl, String model) {

        String jsonPayload = createJsonPayload(message, model);

        RequestBody body = RequestBody.create(jsonPayload, JSON);


        return new Request.Builder()
                .url(apiUrl)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();
    }

private String createJsonPayload(String message, String model) {
    return "{"
                    + "\"model\": \""+ model +"\","
                    + "\"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}],"
                    + "\"temperature\": 0"
                    + "}";
}
}

