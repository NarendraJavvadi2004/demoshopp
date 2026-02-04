package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.clients.CartClient;
import api.payloads.CartPayload;
import io.restassured.response.Response;
import uiApi.utilities.RetryAnalyzer;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CartApiTest {

    private static final Logger logger = LogManager.getLogger(CartApiTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "api.tests.AuthApiTest.loginSuccess")
    public void cartLifecycle() {
        logger.info("Starting Cart Lifecycle Test");

        // Use token from AuthApiTest
        String token = AuthApiTest.authToken;
        logger.info("Using token from AuthApiTest: {}", token);

        // CREATE
        CartPayload cart = new CartPayload();
        cart.setUserId(1);
        cart.setProductIds(Arrays.asList(1, 2, 3));

        logger.info("Creating cart with payload: {}", cart);
        Response resCreate = CartClient.createCart(cart, token);
        logger.info("Create Response: Status={}, Body={}", resCreate.getStatusCode(), resCreate.getBody().asString());
        Assert.assertTrue(resCreate.getStatusCode() >= 200 && resCreate.getStatusCode() < 300, "Cart creation failed");

        int id = resCreate.jsonPath().getInt("id");
        logger.info("Cart created with ID: {}", id);

        // READ
        logger.info("Reading cart with ID: {}", id);
        Response resRead = CartClient.getCartById(id, token);
        logger.info("Read Response: Status={}, Body={}", resRead.getStatusCode(), resRead.getBody().asString());
        Assert.assertEquals(resRead.getStatusCode(), 200, "Cart read failed");

        // UPDATE
        cart.setProductIds(Arrays.asList(2, 3));
        logger.info("Updating cart with ID={} to new productIds={}", id, cart.getProductIds());
        Response resUpdate = CartClient.updateCart(id, cart, token);
        logger.info("Update Response: Status={}, Body={}", resUpdate.getStatusCode(), resUpdate.getBody().asString());
        Assert.assertEquals(resUpdate.getStatusCode(), 200, "Cart update failed");

        // DELETE
        logger.info("Deleting cart with ID: {}", id);
        Response resDelete = CartClient.deleteCart(id, token);
        logger.info("Delete Response: Status={}, Body={}", resDelete.getStatusCode(), resDelete.getBody().asString());
        Assert.assertEquals(resDelete.getStatusCode(), 200, "Cart deletion failed");

        logger.info("Cart Lifecycle Test completed successfully for ID={}", id);
    }
}