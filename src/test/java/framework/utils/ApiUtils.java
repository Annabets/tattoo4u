package framework.utils;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;

import java.io.File;
import java.util.Map;

public class ApiUtils {

    public static JSONArray getRequest(String url) {
        HttpResponse<JsonNode> jsonResponse = Unirest.get(url).asJson();
        return jsonResponse.getBody().getArray();
    }

    public static JsonNode getRequestObject(String url) {
        HttpResponse<JsonNode> jsonResponse = Unirest.get(url).asJson();
        return jsonResponse.getBody();
    }

    public static int getRequestSize(String url) {
        HttpResponse<JsonNode> jsonResponse = Unirest.get(url).asJson();
        return jsonResponse.getBody().getArray().length();
    }

    public static void postApplicationJsonRequest(String url) {
        HttpResponse<JsonNode> jsonResponse = Unirest.post(url).asJson();
    }

    public static HttpResponse<JsonNode>  postApplicationJsonRequest(String url, Map<String, Object> fields) {
        return  Unirest.post(url).header("Content-Type", "application/json").body(fields).asJson();
    }

    public static String postRequestFile(String uri, String pathOfFile, String typeOfFile) {
        HttpResponse<JsonNode> jsonResponse = Unirest.post(uri).field(typeOfFile, new File(pathOfFile)).asJson();
        return jsonResponse.getBody().toString();
    }

}