package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.clients.UserClient;
import io.restassured.response.Response;
import uiApi.utilities.DataP;
import uiApi.utilities.RetryAnalyzer;
import api.payloads.UserPayload;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserApiTest {

    private static final Logger logger = LogManager.getLogger(UserApiTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class,
          dataProvider = "UserApi",
          dataProviderClass = DataP.class)
    public void userLifecycle(String username, String email, String password) {
        logger.info("Starting User Lifecycle Test with username={}, email={}", username, email);

        // CREATE
        UserPayload user = new UserPayload();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);

        logger.info("Creating user with payload: {}", user);
        Response resCreate = UserClient.createUser(user);
        logger.info("Create Response: Status={}, Body={}", resCreate.getStatusCode(), resCreate.getBody().asString());
        Assert.assertTrue(resCreate.getStatusCode() >= 200 && resCreate.getStatusCode() < 300, "User creation failed");
        int id = resCreate.jsonPath().getInt("id");
        logger.info("User created with ID: {}", id);

        // READ
        logger.info("Reading user with ID: {}", id);
        Response resRead = UserClient.readUser(id);
        logger.info("Read Response: Status={}, Body={}", resRead.getStatusCode(), resRead.getBody().asString());
        Assert.assertEquals(resRead.getStatusCode(), 200, "User read failed");

        // UPDATE
        user.setUserName("updated_" + username);
        logger.info("Updating user with ID={} to new username={}", id, user.getUserName());
        Response resUpdate = UserClient.updateUser(id, user);
        logger.info("Update Response: Status={}, Body={}", resUpdate.getStatusCode(), resUpdate.getBody().asString());
        Assert.assertEquals(resUpdate.getStatusCode(), 200, "User update failed");

        // DELETE
        logger.info("Deleting user with ID: {}", id);
        Response resDelete = UserClient.deleteUser(id);
        logger.info("Delete Response: Status={}, Body={}", resDelete.getStatusCode(), resDelete.getBody().asString());
        Assert.assertEquals(resDelete.getStatusCode(), 200, "User deletion failed");

        logger.info("User Lifecycle Test completed successfully for ID={}", id);
    }
}