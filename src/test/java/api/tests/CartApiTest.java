package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.clients.CartClient;
import api.payloads.CartPayload;
import io.restassured.response.Response;
import uiApi.utilities.RetryAnalyzer;

import java.util.Arrays;

public class CartApiTest {

    @Test(retryAnalyzer=RetryAnalyzer.class,dependsOnMethods = "api.tests.AuthApiTest.loginSuccess")
    public void cartLifecycle() {
        // Use token from AuthApiTest
        String token = AuthApiTest.authToken;

        CartPayload cart = new CartPayload();
        cart.setUserId(1);
        cart.setProductIds(Arrays.asList(1, 2, 3));

        Response resCreate = CartClient.createCart(cart, token);
        resCreate.then().log().all();
        int id = resCreate.jsonPath().getInt("id");
        Assert.assertTrue(resCreate.getStatusCode() >= 200);

        Response resRead = CartClient.getCartById(id, token);
        resRead.then().log().all();
        Assert.assertEquals(resRead.getStatusCode(), 200);

        cart.setProductIds(Arrays.asList(2, 3));
        Response resUpdate = CartClient.updateCart(id, cart, token);
        resUpdate.then().log().all();
        Assert.assertEquals(resUpdate.getStatusCode(), 200);

        Response resDelete = CartClient.deleteCart(id, token);
        resDelete.then().log().all();
        Assert.assertEquals(resDelete.getStatusCode(), 200);
    }
}