package com.scompany;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String itemName= "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("saj@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sheza@123");
		driver.findElement(By.id("login")).click();
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//Iterate through the list of items in the page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']")));
		List<WebElement> items= driver.findElements(By.xpath("//div[@class='card-body']"));
		
		WebElement listitem= items.stream().filter(item->item.findElement(By.tagName("b")).getText().equals(itemName)).findFirst().orElse(null);
		
		listitem.findElement(By.cssSelector("button:last-of-type")).click();

		
		//waiting till the toast message is visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//waiting for the refresh animation to be invisible
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartItems= driver.findElements(By.cssSelector("cartSection.h3"));
		cartItems.stream().anyMatch(cartItem->cartItem.getText().equals(itemName));
		//btn.btn-primary
	}

}
