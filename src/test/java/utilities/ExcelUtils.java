package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;
	
	public ExcelUtils(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
	
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			workbook.close();
			fis.close();
			return rowCount;
	
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		int cellCount = sheet.getRow(rowNum).getLastCellNum();
		workbook.close();
		fis.close();
		return cellCount;

}
	
	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			//data=cell.toString();
			data = formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data="";
		}
		workbook.close();
		fis.close();
		return data;

}
	
	public void setCellData(String sheetName, int rowNum, int cellNum, String data) throws IOException {
		
		File excelFile = new File(path);
		if(!excelFile.exists()) {
		workbook = new XSSFWorkbook(fis);
		fos = new FileOutputStream(path);
		workbook.write(fos);
		}
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		
		if(workbook.getSheetIndex(sheetName) == -1) { //If sheet not exist then create new sheet
			workbook.createSheet();
		}
		sheet = workbook.getSheet(sheetName);
		
		if(sheet.getRow(rowNum) == null) { //If row not exists then create new row
			sheet.createRow(rowNum);
		}
		row = sheet.getRow(rowNum);
	
		cell = row.createCell(cellNum);
		cell.setCellValue(data);
		fos = new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();

}
	
	public void fillGreenColor(String sheetName, int rowNum, int cellNum) throws IOException {
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		style = workbook.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fos = new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
		
	}
	
public void fillRedColor(String sheetName, int rowNum, int cellNum) throws IOException {
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		style = workbook.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fos = new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
		
	}
	
	


}
