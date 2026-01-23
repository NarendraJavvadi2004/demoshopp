package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.clients.AuthClient;
import api.payloads.AuthPayload;
import io.restassured.response.Response;
import uiApi.utilities.ConfigReader;
import uiApi.utilities.RetryAnalyzer;

public class AuthApiTest {

    // Static variable to hold token for reuse
    public static String authToken;

    @Test(retryAnalyzer=RetryAnalyzer.class)
    public void loginSuccess() {
        AuthPayload payload = new AuthPayload();
        payload.setUsername(ConfigReader.getProperty("ApiUsername"));
        payload.setPassword(ConfigReader.getProperty("ApiPassword"));

        Response res = AuthClient.login(payload);
        res.then().log().all();

        int statusCode = res.getStatusCode();
        Assert.assertTrue(statusCode == 200 || statusCode == 201,
                "Login should succeed, but got: " + statusCode);

        authToken = res.jsonPath().getString("token");
        Assert.assertNotNull(authToken, "Token should not be null");
    }

    @Test(retryAnalyzer=RetryAnalyzer.class)
    public void loginFailure() {
        AuthPayload payload = new AuthPayload();
        payload.setUsername("wrongUser");
        payload.setPassword("wrongPass");

        Response res = AuthClient.login(payload);
        res.then().log().all();

        Assert.assertEquals(res.getStatusCode(), 401, "Login should fail");
    }
}