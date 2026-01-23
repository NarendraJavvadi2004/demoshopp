package api.clients;

import static io.restassured.RestAssured.*;
import api.endPoints.CartEndpoints;
import api.payloads.CartPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CartClient {

    // CREATE
    public static Response createCart(CartPayload payload, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(CartEndpoints.create);
    }

    // READ
    public static Response getCartById(int id, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .get(CartEndpoints.getById);
    }

    // UPDATE
    public static Response updateCart(int id, CartPayload payload, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(payload)
                .put(CartEndpoints.update);
    }

    // DELETE
    public static Response deleteCart(int id, String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .delete(CartEndpoints.delete);
    }
}