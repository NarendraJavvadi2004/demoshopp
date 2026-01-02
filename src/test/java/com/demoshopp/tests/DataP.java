package com.demoshopp.tests;

import org.testng.annotations.DataProvider;

import com.demoshopp.pageutilities.ExcelUtil;

public class DataP {
  String DataPath = System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx";
	 @DataProvider(name = "loginData")
	    public Object[][] getLoginData() {
	        // Reads all rows from TestData.xlsx, LoginSheet
	        return ExcelUtil.getSheetData(DataPath, "LoginSheet");
	    }
	 
	 
	 @DataProvider(name = "registerData")
	    public Object[][] getRegisterData() {
	        // Reads all rows from TestData.xlsx, Sheet1
	        return ExcelUtil.getSheetData(DataPath, "RegisterSheet");
	    }

}
