package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.clients.ProductsClient;
import api.payloads.ProductsPayload;
import io.restassured.response.Response;
import uiApi.utilities.RetryAnalyzer;

public class ProductApiTest {

	  @Test(retryAnalyzer=RetryAnalyzer.class)
    public void productLifecycle() {
        // CREATE
        ProductsPayload product = new ProductsPayload();
        product.setTitle("Test Product");
        product.setPrice(99.99);
        product.setDescription("Product is verified+");
        product.setCategory("electronics");
        product.setImage("https://i.pravatar.cc");

        Response resCreate = ProductsClient.createProduct(product);
        resCreate.then().log().all();
        int productId = resCreate.jsonPath().getInt("id");
        Assert.assertTrue(resCreate.getStatusCode() >= 200);

        // READ
        Response resRead = ProductsClient.getProductById(productId);
        resRead.then().log().all();
        Assert.assertEquals(resRead.getStatusCode(), 200);

        // UPDATE
        product.setTitle("Updated Product ");
        product.setPrice(149.99);
        Response resUpdate = ProductsClient.updateProduct(productId, product);
        resUpdate.then().log().all();
        Assert.assertEquals(resUpdate.getStatusCode(), 200);

        // DELETE
        Response resDelete = ProductsClient.deleteProduct(productId);
        resDelete.then().log().all();
        Assert.assertEquals(resDelete.getStatusCode(), 200);
    }
}