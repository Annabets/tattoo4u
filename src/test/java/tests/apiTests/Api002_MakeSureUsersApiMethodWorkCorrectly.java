package tests.apiTests;

import com.fasterxml.jackson.databind.JsonNode;
import constants.apiConstants.ApiKeysConstants;
import constants.apiConstants.ResponsesStatusConstants;
import framework.utils.CustomLogger;
import framework.utils.JsonWorker;
import framework.utils.PropertiesWorker;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests.ApiSiteRequests;

public class Api002_MakeSureUsersApiMethodWorkCorrectly {
    @Test
    public void testScenario() {
        CustomLogger.makeStepLog(1, "Making api sign in request");
        JsonNode request = JsonWorker.makeJsonObjectWithString(ApiSiteRequests.makeApiSignIn(
                PropertiesWorker.getTestProperties("exampleAdminUserName"),
                PropertiesWorker.getTestProperties("exampleAdminUserPassword")));

        CustomLogger.makeStepLog(2, "Making api users request");
        System.out.println(request.toString());
        Assert.assertEquals(ApiSiteRequests.makeApiUsersAndGetStatus(request.get(ApiKeysConstants.TOKEN).asText()), ResponsesStatusConstants.STATUS_OK, "Not right status!");
    }
}
