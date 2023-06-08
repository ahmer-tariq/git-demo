package com.tutorialninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int Implicitly_Wait = 10;

	public static String GenerateRandom() {

		Date date = new Date();
		String random = date.toString().replace(" ", "_").replace(":", "_");
		return "abc" + random + "@abc.com";
	}

	public static Object[][] dataSupply(String SheetName) {

		String FilePath = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\TestData.xlsx";
		File excelfile = new File(FilePath);
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(excelfile);
			workbook = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(SheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(1).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				switch (cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				}
			}
		}
		return data;
	}

	public static String screenShots(WebDriver driver, String testName) {

		File srcScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + testName + ".png";
		try {
			FileHandler.copy(srcScreenShot, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}

	public static String screenShot2(WebDriver driver, String testName) {
		File srcScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenShot\\" + testName + ".png";
		try {
			FileHandler.copy(srcScreenShot, new File(destination));
		} catch (IOException e) {

			e.printStackTrace();
		}
		return destination;
	}
}
