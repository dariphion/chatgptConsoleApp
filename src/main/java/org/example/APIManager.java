package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIManager {
    private final OkHttpClient client;
    private final ConfigManager configManager;
    private final RequestBuilder requestBuilder;
    private final ResponseHandler responseHandler;


    public APIManager(ConfigManager configManager) {
        this.configManager = configManager;
        this.client = new OkHttpClient();
        this.requestBuilder = new RequestBuilder();
        this.responseHandler = new ResponseHandler();
    }


    public String sendMessage(String message) {
        String apiUrl = configManager.getApiUrl();
        String apiKey = configManager.getApiKey();
        String model = configManager.model();
        Request request = requestBuilder.buildMessageRequest(apiKey, message, apiUrl, model);

        try {
            Response response = client.newCall(request).execute();
            return responseHandler.handleResponse(response);
        } catch (Exception e) {
            return "Error sending message: " + e.getMessage();
        }
    }
}