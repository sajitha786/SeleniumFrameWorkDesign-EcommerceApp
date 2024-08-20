package com.scompany.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.scompany.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	//List<WebElement> items= driver.findElements(By.xpath("//div[@class='card-body']"));
	
	//PageFactory
	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> items;
	
	@FindBy(css=".ng-animating")
	WebElement refreshIcon;
	
	By itemsBy = By.xpath("//div[@class='card-body']");
	By item = By.tagName("b");
	By addToCart= By.cssSelector("button:last-of-type");
	By toastMessage= By.cssSelector("#toast-container");
	
	public  List<WebElement> getItemList() {
		waitForElementToAppear(itemsBy);
		return items;
	}
	
	public WebElement getProductByName(String itemName) {
		WebElement listitem= getItemList().stream().filter(items->items.findElement(item).getText().equals(itemName)).findFirst().orElse(null);
		return listitem;
	}
	
	public CartPage addProductToCart(String nameOfItem) {
		WebElement item= getProductByName(nameOfItem);
		item.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(refreshIcon);
		CartPage cartPage=new CartPage(driver);
		return cartPage;
		
	}
}
