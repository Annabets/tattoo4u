package tests.apiTests;

import constants.apiConstants.ResponsesStatusConstants;
import framework.utils.CustomLogger;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests.ApiSiteRequests;

public class Api007_MakeSurePostsApiMethodWorkCorrectly {
    @Test
    public void testScenario() {
        CustomLogger.makeStepLog(1, "Making api post request and checking status");
        Assert.assertEquals(ApiSiteRequests.makeApiPostsAndGetStatus(), ResponsesStatusConstants.STATUS_OK, "Not right status!");
    }
}
