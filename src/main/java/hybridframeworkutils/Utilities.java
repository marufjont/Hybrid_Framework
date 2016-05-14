package hybridframeworkutils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	public static WebDriver driver;
	public String[][] testData, testCases, testSteps;
	public String tDID, tCID, tSID, firstName, lastName, execute, browser, url, email, password, welcomeMessage,
			deptCity, destCity, deptDate, retDate, dayTime, resultTestData, resultTestStep, errorAt = " ", errorReport,
			keyword, input, path, tError;
	public Long ltime = 2000L;

	public static WebDriver getDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			return driver = new ChromeDriver();
		}
		if (browser.equalsIgnoreCase("ie") || browser.equalsIgnoreCase("iexplorer")
				|| browser.equalsIgnoreCase("internet explorer")) {
			return driver = new ChromeDriver();
		}
		if (browser.equalsIgnoreCase("htmlunitdriver")) {
			return driver = new HtmlUnitDriver();
		} else
			return driver = new FirefoxDriver();
	}

	public void assignInputVars() {
		if (input.equals("url")) {
			input = url;

		} else if (input.equals("email")) {
			input = email;

		} else if (input.equals("password")) {
			input = password;

		} else if (input.equals("welcomeMessage")) {
			input = welcomeMessage;

		} else if (input.equals("deptCity")) {
			input = deptCity;

		} else if (input.equals("deptDate")) {
			input = deptDate;

		} else if (input.equals("dayTime")) {
			input = dayTime;

		} else if (input.equals("destCity")) {
			input = destCity;

		} else if (input.equals("retDate")) {
			input = retDate;
		}
		// return input;
	}

	public void runTests() {
		if (keyword.equalsIgnoreCase("navigate")) {
			System.out.println("Executing " + keyword);
			System.out.println(input);
			Keywords.navigate(driver, input);
		} else if (keyword.equalsIgnoreCase("click")) {
			System.out.println("Executing " + keyword);
			System.out.println(path);
			Keywords.click(driver, path);
		} else if (keyword.equalsIgnoreCase("clear field")) {
			System.out.println("Executing " + keyword);
			Keywords.clearfield(driver, path);
			System.out.println("field cleared");
		} else if (keyword.equalsIgnoreCase("enter field")) {
			System.out.println("Executing " + keyword);
			System.out.println(path + " " + input);
			Keywords.enterField(driver, path, input);

		} else if (keyword.equalsIgnoreCase("select dropdown")) {
			System.out.println("Executing " + keyword);
			System.out.println(path + " " + input);
			Keywords.selectDropDown(driver, path, input);
			System.out.println("done");
		} else if (keyword.equalsIgnoreCase("wait")) {
			System.out.println("Executing " + keyword);
			try {
				Keywords.waitFor(ltime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("wait over");
		} else if (keyword.equalsIgnoreCase("verify element present")) {
			System.out.println("Executing " + keyword);
			System.out.println(path + " " + input);
			Keywords.verifyElementPresent(driver, path);

			System.out.println("done");

		} else if (keyword.equalsIgnoreCase("verify text")) {
			System.out.println("Executing " + keyword);
			System.out.println(path + " " + input);
			if (Keywords.verifyText(driver, path, input)) {
				System.out.println(" passed verification");
			} else {
				System.out.println("did not pass verification");
			}
		} else {
			System.out.println("keyword " + keyword + " did not match at " + tSID);
		}
	}

	/**************************
	 * Excel functions below *
	 *************************/
	private static int xRows;
	private static int xCols;
	private static XSSFSheet mySheet;

	public static String[][] readXL(String sPath, String iSheet) throws Exception {

		String[][] xData = null;

		File myxl = new File(sPath); // first create a file object providing the
										// path to that file

		if (sPath.toLowerCase().endsWith("xlsx")) {

			// Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(myxl);

			// Create Workbook instance for xlsx/xls file input stream
			Workbook myWB = null;
			myWB = new XSSFWorkbook(fis);

			XSSFSheet mySheet = (XSSFSheet) myWB.getSheet(iSheet); // create
																	// object of
																	// a
																	// specific
																	// sheet
																	// from the
																	// Excel
																	// file
			myWB.getSheet(iSheet);
			int xRows = mySheet.getLastRowNum() + 1; // number of all rows
			int xCols = mySheet.getRow(0).getLastCellNum(); // number of all
															// columns

			xData = new String[xRows][xCols];

			for (int i = 0; i < xRows; i++) {
				XSSFRow row = mySheet.getRow(i);
				for (int j = 0; j < xCols; j++) {
					XSSFCell cell = row.getCell(j);
					String value = cellToStringx(cell);

					xData[i][j] = value;
				}
			}
		} else {
			File myxl1 = new File(sPath); // first create a file object
											// providing the path to that file
			FileInputStream myStream = new FileInputStream(myxl1); // create
																	// input
																	// stream
																	// object
																	// using the
																	// file
																	// object
																	// created
																	// above

			HSSFWorkbook myWB = new HSSFWorkbook(myStream); // WorkBook is an
															// Apache plugin the
															// connects and
															// reads from Excel
			HSSFSheet mySheet = myWB.getSheet(iSheet); // create object of a
														// specific sheet from
														// the Excel file

			int xRows = mySheet.getLastRowNum() + 1; // number of all rows
			int xCols = mySheet.getRow(0).getLastCellNum(); // number of all
															// columns

			xData = new String[xRows][xCols];

			for (int i = 0; i < xRows; i++) {
				HSSFRow row = mySheet.getRow(i);
				for (int j = 0; j < xCols; j++) {
					HSSFCell cell = row.getCell(j);
					String value = cellToString(cell);

					xData[i][j] = value;
				}
			}
		}
		return xData;

	}

	public static String cellToString(HSSFCell cell) {
		// This function will convert an object of type excel to a string value
		int type = cell.getCellType();
		Object result;
		switch (type) {
		case XSSFCell.CELL_TYPE_NUMERIC:
			// 0
			result = cell.getNumericCellValue();

			break;
		case XSSFCell.CELL_TYPE_STRING:
			// 1
			result = cell.getStringCellValue();

			break;
		case XSSFCell.CELL_TYPE_FORMULA:
			// 2
			throw new RuntimeException("We can't evaluate formulas in Java");
		case XSSFCell.CELL_TYPE_BLANK:
			// 3
			result = "%";
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			// 4
			result = cell.getBooleanCellValue();
			break;

		case XSSFCell.CELL_TYPE_ERROR:
			// 5
			throw new RuntimeException("This cell has an error");

		default:

			throw new RuntimeException("We  don't support this cell type " + type);
		}
		return result.toString();
	}

	public static String cellToStringx(XSSFCell cell) {
		// This function will convert an object of type excel to a string value
		int type = cell.getCellType();
		Object result;

		try {
			Date dd = cell.getDateCellValue();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			result = sdf.format(dd);
		} catch (Exception e) {
			switch (type) {
			case XSSFCell.CELL_TYPE_NUMERIC:
				// 0
				result = cell.getNumericCellValue();

				break;
			case XSSFCell.CELL_TYPE_STRING:
				// 1
				result = cell.getStringCellValue();

				break;
			case XSSFCell.CELL_TYPE_FORMULA:
				// 2
				throw new RuntimeException("We can't evaluate formulas in Java");
			case XSSFCell.CELL_TYPE_BLANK:
				// 3
				result = "%";
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN:
				// 4
				result = cell.getBooleanCellValue();
				break;

			case XSSFCell.CELL_TYPE_ERROR:
				// 5
				throw new RuntimeException("This cell has an error");
			default:

				throw new RuntimeException("We  don't support this cell type " + type);
			}
		}
		return result.toString();
	}

	public static void writeXLSX(String sPath, String iSheet, String xData[][]) throws Exception {

		if (sPath.endsWith("xlsx")) {

			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(iSheet);

			int xR_TS = xData.length;
			int xC_TS = xData[0].length;
			for (int myrow = 0; myrow < xR_TS; myrow++) {
				Row row = sheet.createRow(myrow);
				for (int mycol = 0; mycol < xC_TS; mycol++) {
					XSSFCell cell = (XSSFCell) row.createCell(mycol);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(xData[myrow][mycol]);

				}

				// lets write the excel data to file now
				FileOutputStream fos = new FileOutputStream(sPath);
				workbook.write(fos);
				fos.close();
			}
		} else if (sPath.endsWith("xls")) {
			File outFile = new File(sPath);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet osheet = wb.createSheet(iSheet);
			int xR_TS = xData.length;
			int xC_TS = xData[0].length;

			for (int myrow = 0; myrow < xR_TS; myrow++) {
				HSSFRow row = osheet.createRow(myrow);
				for (int mycol = 0; mycol < xC_TS; mycol++) {
					HSSFCell cell = row.createCell(mycol);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(xData[myrow][mycol]);
				}
				FileOutputStream fOut = new FileOutputStream(outFile);
				wb.write(fOut);
				fOut.flush();
				fOut.close();
			}
		} else {
			throw new Exception("invalid file name, should be xls or xlsx");
		}
	}

}
