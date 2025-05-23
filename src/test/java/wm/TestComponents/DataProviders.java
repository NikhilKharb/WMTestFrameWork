package wm.TestComponents;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders {

	@Test(dataProvider = "LoginData")
	public void getDatas(String order, String email, String pass, String msg) {
		System.out.println(email);
	}

	@DataProvider(name = "LoginData")
	public Object[][] getData() throws IOException {
	    String path = "C:\\Users\\kharb\\eclipse-workspace\\WM\\testData\\Data.xlsx";
	    ExcelUtility xlutil = new ExcelUtility(path);

	    int totalRows = xlutil.getRowCount("Data"); // Includes header
	    int totalCols = xlutil.getCellCount("Data", 1);

	    // Use LinkedHashMap to preserve order
	    Map<Integer, Object[]> orderedData = new LinkedHashMap<>();

	    for (int i = 1; i < totalRows; i++) { // Skip header (row 0)
	        Object[] rowData = new Object[totalCols];
	        for (int j = 0; j < totalCols; j++) {
	            rowData[j] = xlutil.getCellData("Data", i, j);
	        }
	        orderedData.put(i, rowData); // Key = row number (preserves order)
	    }

	    // Convert to Object[][]
	    return orderedData.values().toArray(new Object[0][]);
	}
}
