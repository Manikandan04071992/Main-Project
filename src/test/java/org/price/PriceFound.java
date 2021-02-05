package org.price;

import org.baseclass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PriceFound extends BaseClass {
	
	public static void main(String[] args) {
		
		getDriver();
		getURL("https://www.makemytrip.com/flights/");
		
		WebElement findElement = driver.findElement(By.xpath("//a[text()='Search']"));
		findElement.click();
		
		
	}

}
