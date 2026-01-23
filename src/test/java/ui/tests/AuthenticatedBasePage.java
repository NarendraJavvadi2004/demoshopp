package ui.tests;

import org.testng.annotations.BeforeMethod;

import ui.base.Basepage;
import ui.base.DriverManager;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import uiApi.utilities.ConfigReader;

public class AuthenticatedBasePage extends Basepage {
    @BeforeMethod
    public void loginOnce() {
        HomePage home = new HomePage(DriverManager.getDriver());
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        if (home.isUserLoggedOut()) {
            home.clickLogin();
            // Use fixed credentials for flow tests
            loginPage.login(ConfigReader.getProperty("ui.username"), ConfigReader.getProperty("ui.password"));
        }
    }
}