package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.clients.UserClient;
import io.restassured.response.Response;
import uiApi.utilities.DataP;
import uiApi.utilities.RetryAnalyzer;
import api.endPoints.*;
import api.payloads.UserPayload;
public class UserApiTest {
	@Test(  retryAnalyzer=RetryAnalyzer.class,dataProvider = "UserApi", dataProviderClass = DataP.class)
	public void userLifecycle(String username, String email, String password) {
	    // CREATE
	    UserPayload user = new UserPayload();
	    user.setUserName(username);
	    user.setEmail(email);
	    user.setPassword(password);

	    Response resCreate = UserClient.createUser(user);
	    Assert.assertTrue(resCreate.getStatusCode() >= 200 && resCreate.getStatusCode() < 300);
	    int id = resCreate.jsonPath().getInt("id");

	    // READ
	    Response resRead = UserClient.readUser(id);
	    Assert.assertEquals(resRead.getStatusCode(), 200);

	    // UPDATE
	    user.setUserName("updated_" + username);
	    Response resUpdate = UserClient.updateUser(id, user);
	    Assert.assertEquals(resUpdate.getStatusCode(), 200);

	    // DELETE
	    Response resDelete = UserClient.deleteUser(id);
	    Assert.assertEquals(resDelete.getStatusCode(), 200);
	}}