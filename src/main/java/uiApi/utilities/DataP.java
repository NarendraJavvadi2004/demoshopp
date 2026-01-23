package uiApi.utilities;

import org.testng.annotations.DataProvider;

public class DataP {
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelUtil.getSheetData("TestData.xlsx", "LoginSheet");
    }

    @DataProvider(name = "registerData")
    public Object[][] getRegisterData() {
        return ExcelUtil.getSheetData("TestData.xlsx", "RegisterSheet");
    }
    @DataProvider(name = "UserApi")
    public Object[][] getUserAPiData() {
        return ExcelUtil.getSheetData("TestData.xlsx", "UserAPi");
    }
}