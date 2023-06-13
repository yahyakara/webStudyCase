package studyCase.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static List<JsonObject> getJsonList(String jsonFilePath, String objectName) {
        JsonArray jsonArray = parseJsonArray(jsonFilePath, objectName);
        List<JsonObject> resultList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject itemObject = jsonArray.get(i).getAsJsonObject();
            resultList.add(itemObject);
        }
        return resultList;
    }

    public static JsonObject getJsonObject(String jsonFilePath, String objectName) {
        return parseJsonObject(jsonFilePath, objectName);
    }

    private static JsonArray parseJsonArray(String jsonFilePath, String objectName) {
        JsonObject rootObject = parseJsonFile(jsonFilePath);
        if (rootObject != null && rootObject.has(objectName)) {
            return rootObject.getAsJsonArray(objectName);
        }
        return new JsonArray();
    }

    private static JsonObject parseJsonObject(String jsonFilePath, String objectName) {
        JsonObject rootObject = parseJsonFile(jsonFilePath);
        if (rootObject != null && rootObject.has(objectName)) {
            return rootObject.getAsJsonObject(objectName);
        }
        return null;
    }

    private static JsonObject parseJsonFile(String jsonFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath))) {
            JsonParser jsonParser = new JsonParser();
            return jsonParser.parse(reader).getAsJsonObject();
        } catch (Exception e) {
            System.out.println("Hata: JSON dosyası okunurken bir sorun oluştu.");
            return null;
        }
    }
}
