package com.demoshopp.tests;

import org.testng.annotations.DataProvider;

import com.demoshopp.pageutilities.ExcelUtil;

public class DataP {
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelUtil.getSheetData("TestData.xlsx", "LoginSheet");
    }

    @DataProvider(name = "registerData")
    public Object[][] getRegisterData() {
        return ExcelUtil.getSheetData("TestData.xlsx", "RegisterSheet");
    }
}