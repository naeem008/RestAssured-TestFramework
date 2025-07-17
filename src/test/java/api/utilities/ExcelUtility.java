package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtility {

    public static Object[][] getTestData(String excelPath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(excelPath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount - 1][colCount]; // skip header

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = getCellValueAsString(cell);
            }
        }

        workbook.close();
        fis.close();
        return data;
    }

    public static Object[][] getDeleteUserData(String excelPath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(excelPath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();

        Object[][] data = new Object[rowCount - 1][1]; // Only username

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(1); // Column 1 = username
            data[i - 1][0] = getCellValueAsString(cell);
        }

        workbook.close();
        fis.close();
        return data;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }
}