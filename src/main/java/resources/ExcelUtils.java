package resources;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	public static String[][] getTableArray(String FilePath, String SheetName) throws Exception {
		String[][] tabArray = null;
		FileInputStream ExcelFile = new FileInputStream(FilePath);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int ci, cj;
		int totalRows = ExcelWSheet.getLastRowNum();
		// you can write a function as well to get Column count
		int totalCols = 2;
		tabArray = new String[totalRows][totalCols];
		ci = 0;
		for (int i = 1; i <= totalRows; i++, ci++) {
			cj = 0;
			for (int j = 0; j < totalCols; j++, cj++) {
				tabArray[ci][cj] = getCellData(i, j);
			}
		}
		return tabArray;
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		return Cell.getStringCellValue();

	}

	public static String getItemData(int RowNum, int ColNum) throws Exception {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//resources//items.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		XSSFCell Cell = sheet.getRow(RowNum).getCell(ColNum);
		return Cell.getStringCellValue();

	}
}
