// AuthClient.java
package api.clients;
import static io.restassured.RestAssured.*;
import api.endPoints.AuthEndpoints;
import api.payloads.AuthPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthClient {
    public static Response login(AuthPayload payload) {
        return given().contentType(ContentType.JSON).body(payload).post(AuthEndpoints.login);
    }
}
