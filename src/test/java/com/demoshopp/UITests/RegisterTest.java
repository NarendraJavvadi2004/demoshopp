package com.demoshopp.UITests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demoshopp.base.Basepage;
import com.demoshopp.base.DriverManager;
import com.demoshopp.pages.HomePage;
import com.demoshopp.pages.RegisterPage;
import com.demoshopp.pageutilities.RetryAnalyzer;

public class RegisterTest extends Basepage {
	
    @Test(dataProvider = "registerData",
    		dataProviderClass = DataP.class,
    		groups = {"Sanity","Regression"},
    		retryAnalyzer = RetryAnalyzer.class)
    public void verifyUserCanRegisterSuccessfully(String gender, String firstName, String lastName, String password) {
        RegisterPage registerPage = new RegisterPage(DriverManager.getDriver());
        HomePage home = new HomePage(DriverManager.getDriver());
        home.clickRegister();

        // Generate unique email every run
        String email = firstName.toLowerCase() + System.currentTimeMillis() + "@testmail.com";

        // Perform registration
        registerPage.registerUser(gender, firstName, lastName, email, password);

        // Assert success message
        String expectedMessage = "Your registration completed";
        String actualMessage = registerPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Registration success message mismatch!");
    }
}