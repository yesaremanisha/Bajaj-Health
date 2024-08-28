package com.bajajhealth;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {
    public static String getDestinationValue(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return findDestination(jsonElement);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String findDestination(JsonElement jsonElement) {
        if (jsonElement.isJsonObject()) {
            JsonObject obj = jsonElement.getAsJsonObject();
            if (obj.has("destination")) {
                return obj.get("destination").getAsString();
            }
            for (String key : obj.keySet()) {
                String result = findDestination(obj.get(key));
                if (result != null) {
                    return result;
                }
            }
        } else if (jsonElement.isJsonArray()) {
            JsonArray array = jsonElement.getAsJsonArray();
            for (JsonElement element : array) {
                String result = findDestination(element);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}
