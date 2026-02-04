package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.clients.AuthClient;
import api.payloads.AuthPayload;
import io.restassured.response.Response;
import uiApi.utilities.ConfigReader;
import uiApi.utilities.RetryAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthApiTest {

    private static final Logger logger = LogManager.getLogger(AuthApiTest.class);

    // Static variable to hold token for reuse
    public static String authToken;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginSuccess() {
        logger.info("Starting loginSuccess test");

        AuthPayload payload = new AuthPayload();
        payload.setUsername(ConfigReader.getProperty("api.username"));
        payload.setPassword(ConfigReader.getProperty("api.password"));

        logger.info("Attempting login with username: {}", payload.getUsername());
        Response res = AuthClient.login(payload);

        int statusCode = res.getStatusCode();
        logger.info("Login Response: Status={}, Body={}", statusCode, res.getBody().asString());

        Assert.assertTrue(
                statusCode == 200 || statusCode == 201,
                "Login should succeed, but got: " + statusCode
        );

        authToken = res.jsonPath().getString("token");
        logger.info("Extracted token: {}", authToken);

        Assert.assertNotNull(authToken, "Token should not be null");
        logger.info("loginSuccess test completed successfully");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginFailure() {
        logger.info("Starting loginFailure test");

        AuthPayload payload = new AuthPayload();
        payload.setUsername("wrongUser");
        payload.setPassword("wrongPass");

        logger.info("Attempting login with invalid credentials: {}", payload.getUsername());
        Response res = AuthClient.login(payload);

        int statusCode = res.getStatusCode();
        logger.info("Login Failure Response: Status={}, Body={}", statusCode, res.getBody().asString());

        Assert.assertEquals(
                statusCode,
                401,
                "Login should fail"
        );

        logger.info("loginFailure test completed successfully");
    }
}