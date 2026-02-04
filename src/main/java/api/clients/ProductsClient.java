package api.clients;

import static io.restassured.RestAssured.*;

import api.endPoints.ProductsEndpoints;
import api.payloads.ProductsPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductsClient {

    private static final Logger logger = LogManager.getLogger(ProductsClient.class);

    // CREATE
    public static Response createProduct(ProductsPayload payload) {
        logger.info("Creating product with payload: {}", payload);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post(ProductsEndpoints.create);

        logger.info("Create Product Response: Status={}, Body={}", 
                    response.getStatusCode(), response.getBody().asString());
        return response;
    }

    // READ
    public static Response getAllProducts() {
        logger.info("Fetching all products");
        Response response = given().get(ProductsEndpoints.getAll);

        logger.info("Get All Products Response: Status={}, Body={}", 
                    response.getStatusCode(), response.getBody().asString());
        return response;
    }

    public static Response getProductById(int id) {
        logger.info("Fetching product with ID: {}", id);
        Response response = given()
                .pathParam("id", id)
                .get(ProductsEndpoints.getById);

        logger.info("Get Product By ID Response: Status={}, Body={}", 
                    response.getStatusCode(), response.getBody().asString());
        return response;
    }

    // UPDATE
    public static Response updateProduct(int id, ProductsPayload payload) {
        logger.info("Updating product with ID: {} and payload: {}", id, payload);
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(payload)
                .put(ProductsEndpoints.update);

        logger.info("Update Product Response: Status={}, Body={}", 
                    response.getStatusCode(), response.getBody().asString());
        return response;
    }

    // DELETE
    public static Response deleteProduct(int id) {
        logger.info("Deleting product with ID: {}", id);
        Response response = given()
                .pathParam("id", id)
                .delete(ProductsEndpoints.delete);

        logger.info("Delete Product Response: Status={}, Body={}", 
                    response.getStatusCode(), response.getBody().asString());
        return response;
    }
}