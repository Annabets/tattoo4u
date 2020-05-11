package tests.apiTests;

import com.fasterxml.jackson.databind.JsonNode;
import constants.apiConstants.ApiKeysConstants;
import framework.utils.CustomLogger;
import framework.utils.JsonWorker;
import framework.utils.PropertiesWorker;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import requests.ApiSiteRequests;

public class Api001_MakeSureSignInApiMethodWorkCorrectly {
    @Test
    public void testScenario() {
        CustomLogger.makeStepLog(1, "Making api sign in request");
        JsonNode request = JsonWorker.makeJsonObjectWithString(ApiSiteRequests.makeApiSignIn(
                PropertiesWorker.getTestProperties("exampleAdminUserName"),
                PropertiesWorker.getTestProperties("exampleAdminUserPassword")));
        CustomLogger.makeStepLog(2, "Checking that request is right");
        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertEquals(request.get(ApiKeysConstants.USERNAME).asText(),
                PropertiesWorker.getTestProperties("exampleAdminUserName"), "Not right user name!");
        softAssertion.assertEquals(request.get(ApiKeysConstants.EMAIL).asText(),
                PropertiesWorker.getTestProperties("exampleAdminUserEmail"), "Not right user email!");
        softAssertion.assertFalse(request.get(ApiKeysConstants.TOKEN).isNull(), "Token doesn't exist!");
        softAssertion.assertAll();
    }
}
