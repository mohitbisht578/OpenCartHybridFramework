package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		  
		String path= "./testData/LoginData.xlsx";     //taking excel file form testData
		ExcelUtils excelUtils = new ExcelUtils(path); //creating an object for ExcelUtils
		
		int totalRows = excelUtils.getRowCount("Sheet1");
		int totalCells = excelUtils.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCells]; //created for two dimension array which can stored
		
		for(int i=1; i<=totalRows; i++) { //1 read the data from excel storing in tow dimension arry
			
			for(int j=0; j<totalCells; j++) { //0 i is row j is cell
				
				loginData[i-1][j] = excelUtils.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
	}
}

