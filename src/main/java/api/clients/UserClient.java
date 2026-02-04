package api.clients;

import static io.restassured.RestAssured.*;

import api.endPoints.UserEndpoints;
import api.payloads.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserClient {

    private static final Logger logger = LogManager.getLogger(UserClient.class);

    public static Response createUser(UserPayload payload) {
        logger.info("Creating user with payload: {}", payload);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post(UserEndpoints.postUrl);

        logger.info("Create User Response: Status={}, Body={}", response.getStatusCode(), response.getBody().asString());
        return response;
    }

    public static Response readUser(int id) {
        logger.info("Reading user with ID: {}", id);
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .get(UserEndpoints.getUrlById);

        logger.info("Read User Response: Status={}, Body={}", response.getStatusCode(), response.getBody().asString());
        return response;
    }

    public static Response updateUser(int id, UserPayload payload) {
        logger.info("Updating user with ID: {} and payload: {}", id, payload);
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(payload)
                .put(UserEndpoints.UpdateUrl);

        logger.info("Update User Response: Status={}, Body={}", response.getStatusCode(), response.getBody().asString());
        return response;
    }

    public static Response deleteUser(int id) {
        logger.info("Deleting user with ID: {}", id);
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .delete(UserEndpoints.deleteUrl);

        logger.info("Delete User Response: Status={}, Body={}", response.getStatusCode(), response.getBody().asString());
        return response;
    }
}