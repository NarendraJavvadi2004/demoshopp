package com.demoshopp.tests;

import org.testng.annotations.BeforeMethod;

import com.demoshopp.base.Basepage;
import com.demoshopp.base.DriverManager;
import com.demoshopp.pages.HomePage;
import com.demoshopp.pages.LoginPage;
import com.demoshopp.pageutilities.ConfigReader;

public class AuthenticatedBasePage extends Basepage {
    @BeforeMethod
    public void loginOnce() {
        HomePage home = new HomePage(DriverManager.getDriver());
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        if (home.isUserLoggedOut()) {
            home.clickLogin();
            // Use fixed credentials for flow tests
            loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        }
    }
}