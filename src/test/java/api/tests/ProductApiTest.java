package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.clients.ProductsClient;
import api.payloads.ProductsPayload;
import io.restassured.response.Response;
import uiApi.utilities.RetryAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductApiTest {

    private static final Logger logger = LogManager.getLogger(ProductApiTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void productLifecycle() {
        logger.info("Starting Product Lifecycle Test");

        // CREATE
        ProductsPayload product = new ProductsPayload();
        product.setTitle("Test Product");
        product.setPrice(99.99);
        product.setDescription("Product is verified+");
        product.setCategory("electronics");
        product.setImage("https://i.pravatar.cc");

        logger.info("Creating product with payload: {}", product);
        Response resCreate = ProductsClient.createProduct(product);
        logger.info("Create Response: Status={}, Body={}", resCreate.getStatusCode(), resCreate.getBody().asString());
        Assert.assertTrue(resCreate.getStatusCode() >= 200 && resCreate.getStatusCode() < 300, "Product creation failed");

        int productId = resCreate.jsonPath().getInt("id");
        logger.info("Product created with ID: {}", productId);

        // READ
        logger.info("Reading product with ID: {}", productId);
        Response resRead = ProductsClient.getProductById(productId);
        logger.info("Read Response: Status={}, Body={}", resRead.getStatusCode(), resRead.getBody().asString());
        Assert.assertEquals(resRead.getStatusCode(), 200, "Product read failed");

        // UPDATE
        product.setTitle("Updated Product");
        product.setPrice(149.99);
        logger.info("Updating product with ID={} to new title={} and price={}", productId, product.getTitle(), product.getPrice());
        Response resUpdate = ProductsClient.updateProduct(productId, product);
        logger.info("Update Response: Status={}, Body={}", resUpdate.getStatusCode(), resUpdate.getBody().asString());
        Assert.assertEquals(resUpdate.getStatusCode(), 200, "Product update failed");

        // DELETE
        logger.info("Deleting product with ID: {}", productId);
        Response resDelete = ProductsClient.deleteProduct(productId);
        logger.info("Delete Response: Status={}, Body={}", resDelete.getStatusCode(), resDelete.getBody().asString());
        Assert.assertEquals(resDelete.getStatusCode(), 200, "Product deletion failed");

        logger.info("Product Lifecycle Test completed successfully for ID={}", productId);
    }
}