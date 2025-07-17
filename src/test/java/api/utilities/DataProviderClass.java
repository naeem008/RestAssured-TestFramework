package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.File;

public class DataProviderClass {

	// ✅ Direct absolute path to your Excel file
	String excelPath = "C:/Users/User/eclipse-workspace/Rest_Assured_Framework/RestAssured-TestFramework/testData/Generated_UserData.xlsx";

	// ✅ Data Provider for full user creation data (8 fields)
	@DataProvider(name = "AllUserData")
	public Object[][] getAllUserData() throws Exception {
		checkExcelFile();

		Object[][] data = ExcelUtility.getTestData(excelPath, "Sheet1");

		System.out.println("✅ Excel Data Loaded:");
		System.out.println("Rows (excluding header): " + data.length);
		System.out.println("Columns: " + data[0].length);

		return data;
	}

	// ✅ Data Provider for username-only delete test
	@DataProvider(name = "DeleteUserData")
	public Object[][] getDeleteUserData() throws Exception {
		checkExcelFile();

		Object[][] data = ExcelUtility.getDeleteUserData(excelPath, "Sheet1");

		System.out.println("✅ Delete User Data Loaded:");
		System.out.println("Rows: " + data.length);
		System.out.println("Columns: " + data[0].length);

		return data;
	}

	// ✅ File existence check
	private void checkExcelFile() {
		File file = new File(excelPath);
		if (!file.exists()) {
			System.out.println("❌ Excel file not found at: " + excelPath);
		} else {
			System.out.println("✅ Excel file found at: " + excelPath);
		}
	}
}
