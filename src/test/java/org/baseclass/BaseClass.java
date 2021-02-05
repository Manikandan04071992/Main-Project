package org.baseclass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BaseClass {

	public static WebDriver driver;

	public static void getDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\anbu\\eclipse-workspace`\\FaceBook\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	
	
	}

	public static void getURL(String url) {
		driver.get(url);

	}

	public void sendText(WebElement element, String data) {
		element.sendKeys(data);
	}

	public void btnClick(WebElement element) {
		element.click();
	}

	public void btnClose() {
		driver.close();
	}

	public void btnQuit() {
		driver.quit();
	}

	public void moveToElement(WebElement element) {
		Actions ac = new Actions(driver);
		ac.moveToElement(element);
	}

	public void selectByIndex( WebElement element,int Index) {
		Select s = new Select(element);
		s.selectByIndex(Index);
	}

	public void getTitle() {
		driver.getTitle();
	}

	public void getText(WebElement element) {
		element.getText();
	}

	public void getCurrentUrl() {
		driver.getCurrentUrl();
	}

	public void getPageSource() {
		driver.getPageSource();
	}

	public void navigateUrl(String url) {
		driver.navigate().to(url);
	}

	public void navigateRefresh() {
		driver.navigate().refresh();
	}

	public void navigateforward() {
		driver.navigate().forward();
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void findElement(String path) {
		driver.findElement(By.xpath(path));

	}

	public void findElements(String path) {
		List<WebElement> findElements = driver.findElements(By.xpath(path));
	}

	public void clear(WebElement element) {
		element.clear();
	}

	public void selectByValue(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByValue(value);
	}

	public void selectByVisibleText(WebElement element, String text) {
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}

	public void getOptions(WebElement element) {
		Select s = new Select(element);
		List<WebElement> options = s.getOptions();
		System.out.println(options);
	}

	public void getAllSelectedOptions(WebElement element) {
		Select s = new Select(element);
		List<WebElement> allSelectedOptions = s.getAllSelectedOptions();

	}

	public void getFirstSelectedOptions(WebElement element) {
		Select s = new Select(element);
		WebElement firstSelectedOption = s.getFirstSelectedOption();
	}

	public void isMultiple(WebElement element) {

		Select s = new Select(element);
		boolean multiple = s.isMultiple();
	}

	public void deSelectByIndex(WebElement element, int index) {
		Select s = new Select(element);
		s.deselectByIndex(index);
	}

	public void deSelectByValue(WebElement element, String value) {
		Select s = new Select(element);
		s.deselectByValue(value);
	}

	public void deSelectByVisibleText(WebElement element, String text) {
		Select s = new Select(element);
		s.deselectByVisibleText(text);
	}

	public void deSelectAll(WebElement element) {
		Select s = new Select(element);
		s.deselectAll();
	}

	public void doubleClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.doubleClick();
	}

	public void contextClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.contextClick(element);
	}

	public void dragAndDrop(WebElement source, WebElement target) {
		Actions ac = new Actions(driver);
		ac.dragAndDrop(source, target).build().perform();
	}

	public void clickAndHold(WebElement source, WebElement target) {
		Actions ac = new Actions(driver);
		ac.clickAndHold(source).moveToElement(target).release().build().perform();
	}

	public void keyDownAndKeyUp(WebElement element, String value) {
		Actions ac = new Actions(driver);
		ac.keyDown(element, Keys.SHIFT).sendKeys(value).keyUp(element, Keys.SHIFT).build().perform();
	}

	public void backSpace() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_BACK_SPACE);
		r.keyRelease(KeyEvent.VK_BACK_SPACE);
	}

	public void keyUp() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_UP);
		r.keyRelease(KeyEvent.VK_UP);
	}

	public void keyDown() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
	}

	public void keyEnter() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	public void keyTab() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
	}
	
	public String readValueFormat(String path,String sheetName,int rowNo,int cellNo) throws IOException {
		
		File f = new File(path);
		
		FileInputStream stream = new FileInputStream(f);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNo);
		Cell cell = row.getCell(cellNo);
		int cellType = cell.getCellType();
		String value =" ";
		if (cellType==1) {
			value = cell.getStringCellValue();
			System.out.println(value);
		}
		
		else if(DateUtil.isCellDateFormatted(cell))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-YYYY");
			Date dateCellValue = cell.getDateCellValue();
			value = dateFormat.format(dateCellValue);
			System.out.println(value);
		}
		
		else
		{
			double numericCellValue = cell.getNumericCellValue();
			
			long l = (long) numericCellValue;
			
			value = String.valueOf(l);
			System.out.println(value);
	
		}
		
		
		
		return value;
		

	}

}
