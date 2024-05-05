package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Response;
import java.io.IOException;

public class ResponseHandler {


    public String handleResponse(Response response) {
        if (response.isSuccessful()) {
            try (response) {
                // storing the response body in a string variable
                assert response.body() != null;
                String responseBody = response.body().string();

                // parse the JSON response to extract data
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
                String result = jsonResponse.getAsJsonArray("choices").get(0).getAsJsonObject().getAsJsonObject("message").get("content").getAsString();

                return result.trim();
            } catch (Exception e) {
                return "Failed to parse response: " + e.getMessage();
            }
        } else {
            // handle errors
            return "API returned an error: " + response.code() + " - " + getErrorMessage(response);
        }
    }


    private String getErrorMessage(Response response) {

        //parse the response for the possible error message
        try (response) {
            assert response.body() != null;
            JsonObject jsonResponse = JsonParser.parseString(response.body().string()).getAsJsonObject();
            return jsonResponse.get("error").getAsJsonObject().get("message").getAsString();
        } catch (IOException | NullPointerException e) {
            return "Unknown error occurred.";
        }
    }


}
