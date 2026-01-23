
	// ProductClient.java
	package api.clients;
	import static io.restassured.RestAssured.*;
	import api.endPoints.ProductsEndpoints;
import api.payloads.ProductsPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

	public class ProductsClient {

	    // CREATE
	    public static Response createProduct(ProductsPayload payload) {
	        return given()
	                .contentType(ContentType.JSON)
	                .body(payload)
	                .post(ProductsEndpoints.create);
	    }

	    // READ
	    public static Response getAllProducts() {
	        return given().get(ProductsEndpoints.getAll);
	    }

	    public static Response getProductById(int id) {
	        return given().pathParam("id", id).get(ProductsEndpoints.getById);
	    }

	    // UPDATE
	    public static Response updateProduct(int id, ProductsPayload payload) {
	        return given()
	                .contentType(ContentType.JSON)
	                .pathParam("id", id)
	                .body(payload)
	                .put(ProductsEndpoints.update);
	    }

	    // DELETE
	    public static Response deleteProduct(int id) {
	        return given().pathParam("id", id).delete(ProductsEndpoints.delete);
	    }
	}

