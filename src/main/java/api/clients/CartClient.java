package api.clients;

import static io.restassured.RestAssured.*;

import api.endPoints.CartEndpoints;
import api.payloads.CartPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CartClient {

    private static final Logger logger = LogManager.getLogger(CartClient.class);

    // CREATE
    public static Response createCart(CartPayload payload, String token) {
        logger.info("Creating cart with payload: {}", payload);
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(CartEndpoints.create);

        logger.info("Create Cart Response: Status={}, Body={}", 
                    response.getStatusCode(), response.getBody().asString());
        return response;
    }

    // READ
    public static Response getCartById(int id, String token) {
        logger.info("Fetching cart with ID: {}", id);
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .get(CartEndpoints.getById);

        logger.info("Get Cart By ID Response: Status={}, Body={}", 
                    response.getStatusCode(), response.getBody().asString());
        return response;
    }

    // UPDATE
    public static Response updateCart(int id, CartPayload payload, String token) {
        logger.info("Updating cart with ID: {} and payload: {}", id, payload);
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(payload)
                .put(CartEndpoints.update);

        logger.info("Update Cart Response: Status={}, Body={}", 
                    response.getStatusCode(), response.getBody().asString());
        return response;
    }

    // DELETE
    public static Response deleteCart(int id, String token) {
        logger.info("Deleting cart with ID: {}", id);
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .delete(CartEndpoints.delete);

        logger.info("Delete Cart Response: Status={}, Body={}", 
                    response.getStatusCode(), response.getBody().asString());
        return response;
    }
}