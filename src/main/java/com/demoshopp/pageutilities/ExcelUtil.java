package com.demoshopp.pageutilities;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;

public class ExcelUtil {

	public static Object[][] getSheetData(String fileName, String sheetName) {
	    Object[][] data;
	    try (InputStream inputStream = ExcelUtil.class.getClassLoader().getResourceAsStream(fileName);
	         Workbook workbook = WorkbookFactory.create(inputStream)) {

	        Sheet sheet = workbook.getSheet(sheetName);
	        if (sheet == null) {
	            throw new RuntimeException("Sheet '" + sheetName + "' not found in " + fileName);
	        }

	        int rowCount = sheet.getPhysicalNumberOfRows();
	        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
	        data = new Object[rowCount - 1][colCount];

	        for (int i = 1; i < rowCount; i++) {
	            Row row = sheet.getRow(i);
	            for (int j = 0; j < colCount; j++) {
	                Cell cell = row.getCell(j);
	                if (cell == null) {
	                    data[i - 1][j] = "";
	                } else {
	                    data[i - 1][j] = cell.toString().trim();
	                }
	            }
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to read Excel data", e);
	    }
	    return data;
	}}