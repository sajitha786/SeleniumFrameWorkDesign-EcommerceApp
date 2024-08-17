package com.scompany;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.scompany.pageobjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String itemName= "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage= new LandingPage(driver);
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
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartItems= driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartItems.stream().anyMatch(cartItem->cartItem.getText().equals(itemName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("uni");
		
		//alternate method to type uni
		//Actions act =new Actions(driver);
		//act.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"uni").build().perform();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//section[contains(@class,'ta-results')]"))));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][4]")).click();
		driver.findElement(By.xpath("//a[contains(@class,'btnn')]")).click();
		
		String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

}
