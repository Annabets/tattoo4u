package tests.apiTests;

import com.fasterxml.jackson.databind.JsonNode;
import constants.apiConstants.ApiKeysConstants;
import constants.apiConstants.ResponsesStatusConstants;
import framework.utils.CustomLogger;
import framework.utils.JsonWorker;
import framework.utils.PropertiesWorker;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import requests.ApiSiteRequests;

public class Api003_004_MakeSureSignUpAndDeleteApiMethodsWorkCorrectly {
    @Test
    public void testScenario() {
       CustomLogger.makeStepLog(1, "Making api sign up request");
        SoftAssert softAssertion= new SoftAssert();
        JsonNode firstRequest = JsonWorker.makeJsonObjectWithString(ApiSiteRequests.makeApiSignUp(PropertiesWorker.getTestProperties("exampleUserNameForSignUp"),
                PropertiesWorker.getTestProperties("exampleUserPasswordForSignUp"),
                PropertiesWorker.getTestProperties("exampleUserEmailForSignUp")));
        System.out.println(firstRequest.toString());
        softAssertion.assertEquals(firstRequest.get(ApiKeysConstants.USERNAME).asText(), PropertiesWorker.getTestProperties("exampleUserNameForSignUp"), "Not right user name!");
        softAssertion.assertEquals(firstRequest.get(ApiKeysConstants.EMAIL).asText(), PropertiesWorker.getTestProperties("exampleUserEmailForSignUp"), "Not right user email!");
        softAssertion.assertFalse(firstRequest.get(ApiKeysConstants.TOKEN).isNull(), "Token doesn't exist!");

        CustomLogger.makeStepLog(2, "Making api sign in request with administrator");
        JsonNode secondRequest = JsonWorker.makeJsonObjectWithString(ApiSiteRequests.makeApiSignIn(
                PropertiesWorker.getTestProperties("exampleAdminUserName"),
                PropertiesWorker.getTestProperties("exampleAdminUserPassword")));

        CustomLogger.makeStepLog(3, "Making api delete request");
        softAssertion.assertEquals(ApiSiteRequests.makeApiDeleteUserAndGetStatus(firstRequest.get(ApiKeysConstants.ID).asText(),  secondRequest.get(ApiKeysConstants.TOKEN).asText()), ResponsesStatusConstants.STATUS_OK, "Not right status!");
        softAssertion.assertAll();
    }
}
