package requests;

import constants.RolesConstants;
import constants.apiConstants.ApiKeysConstants;
import constants.apiConstants.ApiMethodsConstants;
import framework.utils.ApiUtils;
import framework.utils.PropertiesWorker;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.Map;

public class ApiSiteRequests {
    public static String makeApiSignIn(String userName, String password) {
        String apiUrl = PropertiesWorker.getConfigProperties("baseApiUrl") + ApiMethodsConstants.SIGN_IN;
        Map<String, Object> fields = new HashMap<>();
        System.out.println("apiurl - " + apiUrl);
        fields.put(ApiKeysConstants.USERNAME, userName);
        fields.put(ApiKeysConstants.PASSWORD, password);
        return ApiUtils.postApplicationJsonRequest(apiUrl, fields).getBody().toString();
    }

    public static int makeApiUsersAndGetStatus(String adminToken) {
        String apiUrl = PropertiesWorker.getConfigProperties("baseApiUrl") + ApiMethodsConstants.USERS;
        Map<String, String> fields = new HashMap<>();
        fields.put(ApiKeysConstants.AUTHORIZATION, "Bearer_" + adminToken);
        return Unirest.get(apiUrl).headers(fields).asJson().getStatus();
    }

    public static int makeApiCurrentUserAndGetStatus(String adminToken) {
        String apiUrl = PropertiesWorker.getConfigProperties("baseApiUrl") + ApiMethodsConstants.USERS + "/" + ApiMethodsConstants.CURRENT_USER;
        Map<String, String> fields = new HashMap<>();
        fields.put(ApiKeysConstants.AUTHORIZATION, "Bearer_" + adminToken);
        return Unirest.get(apiUrl).headers(fields).asJson().getStatus();
    }

    public static int makeApiSignOutAndGetStatus(String adminToken){
        String apiUrl = PropertiesWorker.getConfigProperties("baseApiUrl") + ApiMethodsConstants.SIGN_OUT;
        Map<String, String> fields = new HashMap<>();
        fields.put(ApiKeysConstants.AUTHORIZATION, "Bearer_" + adminToken);
        return Unirest.get(apiUrl).headers(fields).asJson().getStatus();
    }

    public static String makeApiSignUp(String name, String password, String email) {
        String apiUrl = PropertiesWorker.getConfigProperties("baseApiUrl") + ApiMethodsConstants.SIGN_UP;
        Map<String, Object> fields = new HashMap<>();
        fields.put(ApiKeysConstants.USERNAME, name);
        fields.put(ApiKeysConstants.PASSWORD, password);
        fields.put(ApiKeysConstants.ROLE, RolesConstants.USER);
        fields.put(ApiKeysConstants.EMAIL, email);
        return ApiUtils.postApplicationJsonRequest(apiUrl, fields).getBody().toString();
    }

    public static int makeApiDeleteUserAndGetStatus(String idOfUser, String adminToken) {
        String apiUrl = PropertiesWorker.getConfigProperties("baseApiUrl") + ApiMethodsConstants.USERS + "/" + idOfUser;
        Map<String, String> fields = new HashMap<>();
        fields.put(ApiKeysConstants.AUTHORIZATION, "Bearer_" + adminToken);
        return Unirest.delete(apiUrl).headers(fields).asJson().getStatus();
    }

    public static int makeApiPostsAndGetStatus() {
        String apiUrl = PropertiesWorker.getConfigProperties("baseApiUrl") + ApiMethodsConstants.POSTS;
        return Unirest.get(apiUrl).asJson().getStatus();
    }

    public static int makeApiGetAllStudiosAndGetStatus() {
        String apiUrl = PropertiesWorker.getConfigProperties("baseApiUrl") + ApiMethodsConstants.STUDIOS;
        return Unirest.get(apiUrl).asJson().getStatus();
    }

    public static int makeApiGetAllMastersAndGetStatus() {
        String apiUrl = PropertiesWorker.getConfigProperties("baseApiUrl") + ApiMethodsConstants.MASTERS;
        return Unirest.get(apiUrl).asJson().getStatus();
    }
}
