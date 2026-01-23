package api.clients;

import static io.restassured.RestAssured.*;
import api.endPoints.*;
import api.payloads.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserClient {

    public static Response createUser(UserPayload payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post(UserEndpoints.postUrl);
    }

    public static Response readUser(int id) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .get(UserEndpoints.getUrlById);
    }

    public static Response updateUser(int id, UserPayload payload) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(payload)
                .put(UserEndpoints.UpdateUrl);
    }

    public static Response deleteUser(int id) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .delete(UserEndpoints.deleteUrl);
    }
}