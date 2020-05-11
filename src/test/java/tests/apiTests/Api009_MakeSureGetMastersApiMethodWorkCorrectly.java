package tests.apiTests;

import constants.apiConstants.ResponsesStatusConstants;
import framework.utils.CustomLogger;
import org.testng.Assert;
import org.testng.annotations.Test;
import requests.ApiSiteRequests;

public class Api009_MakeSureGetMastersApiMethodWorkCorrectly {
    @Test
    public void testScenario() {
        CustomLogger.makeStepLog(1, "Making api get masters request and checking status");
        Assert.assertEquals(ApiSiteRequests.makeApiGetAllMastersAndGetStatus(), ResponsesStatusConstants.STATUS_OK, "Not right status!");
    }
}
