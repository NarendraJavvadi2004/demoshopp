// AuthClient.java
package api.clients;

import static io.restassured.RestAssured.*;

import api.endPoints.AuthEndpoints;
import api.payloads.AuthPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthClient {

    private static final Logger logger = LogManager.getLogger(AuthClient.class);

    /**
     * Perform login with given payload
     * @param payload AuthPayload containing username/password
     * @return Response object from login API
     */
    public static Response login(AuthPayload payload) {
        logger.info("Attempting login with payload: {}", payload);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post(AuthEndpoints.login);

        logger.info("Login Response: Status={}, Body={}", 
                    response.getStatusCode(), 
                    response.getBody().asString());

        return response;
    }
}