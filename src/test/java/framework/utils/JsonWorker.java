package framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonWorker {

    public static JsonNode makeJsonObjectWithString(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode object;
        try {
            object = mapper.readValue(jsonString, JsonNode.class);
        } catch (Exception e) {
            object = null;
            CustomLogger.makeErrorLog("Bad Request!");
        }
        return object;
    }
}
