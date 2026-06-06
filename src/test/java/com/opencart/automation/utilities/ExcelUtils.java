package com.opencart.automation.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {

        Object[][] data = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook wb = new XSSFWorkbook(fis);) {
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet " + sheetName + " not found!");
            }
//            Row headerRow = sheet.getRow(0);
//            if (headerRow)
            int rowCount = sheet.getLastRowNum(); //số dòng (không tính header)
            int colCount = sheet.getRow(0).getLastCellNum();    //số cột của dòng header
            data = new Object[rowCount][colCount];
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = getCellValue(cell);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc file "+ filePath, e);
        }

        return data;
    }
    //lấy giá trị của một ô theo nhiều kiểu dữ liệu
    private static String getCellValue(Cell cell) {
//        String data = "";
        if (cell == null) {
            return "";
        }
        try {
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        }catch (Exception e) {
            return "";
        }

    }
}
