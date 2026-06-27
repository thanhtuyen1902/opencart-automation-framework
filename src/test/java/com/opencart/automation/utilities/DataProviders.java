package com.opencart.automation.utilities;

import org.testng.annotations.DataProvider;
import java.io.IOException;

public class DataProviders {
    //DataProvider 1
    @DataProvider(name="negativeRegistrationData")
    public static Object[][] getRegistrationData() {
        String path = "src/test/resources/testData/registration_negative_testdata.xlsx";
        String sheetName = "NegativeRegistration";
        try {
            return ExcelUtils.getExcelData(path, sheetName);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi đọc file", e);
        }
    }

    //DataProvider 2
    @DataProvider(name="LoginData")
    public static Object[][] getLoginData() {
        String path = "src/test/resources/testData/login_testdata.xlsx";
        String sheetName = "LoginData";
        try {
            return ExcelUtils.getExcelData(path, sheetName);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi đọc file", e);
        }
    }
    //DataProvider 3

}
