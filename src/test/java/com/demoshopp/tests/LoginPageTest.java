package com.demoshopp.tests;
import com.demoshopp.pageutilities.RetryAnalyzer;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.demoshopp.base.Basepage;
import com.demoshopp.base.DriverManager;
import com.demoshopp.pages.HomePage;
import com.demoshopp.pages.LoginPage;

public class LoginPageTest extends Basepage {

	@Test(
		    dataProvider = "loginData",
		    dataProviderClass = DataP.class,
		    groups = {"Sanity", "Regression"},
		    retryAnalyzer = RetryAnalyzer.class
		)
    public void testValidLogin(String email, String password) {
         
	HomePage  hm = new HomePage(DriverManager.getDriver());
	LoginPage lg =  new LoginPage(DriverManager.getDriver());
	 hm.clickLogin();
	    lg.login(email,password);
	    String actualEmail = lg.getLoggedInUserEmail();
	    Assert.assertEquals(actualEmail, email,"mismatch");
	    System.out.println("Login test Passed");
	 lg.clickLogout();
	}}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//        // Initialize page objects with driver from DriverManager
//        HomePage home = new HomePage(DriverManager.getDriver());
//        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
//
//        // Navigate to login page
//        home.clickLogin();
//
//        // Perform login
//        loginPage.login(email, password);
//
//        // Verify logged-in user email
//        String actualEmail = loginPage.getLoggedInUserEmail();
//        Assert.assertEquals(actualEmail, email, "Login email mismatch");
//
//        System.out.println("Login test passed for: " + email);
//
//        // Logout after test
//        loginPage.clickLogout();
//    }
//}