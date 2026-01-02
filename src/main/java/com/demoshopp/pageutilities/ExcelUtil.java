package com.demoshopp.pageutilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;

public class ExcelUtil {

    public static Object[][] getSheetData(String filePath, String sheetName) {

        Object[][] data;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

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
                        data[i - 1][j] = cell.toString();
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel data", e);
        }

        return data;
    }
}
