package com.scompany.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.scompany.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> itemsInCart;
	
	public boolean verifyProductDisplay(String itemName) {
		Boolean match = itemsInCart.stream().anyMatch(cartItem->cartItem.getText().equals(itemName));
		return match;
	}
	
	
	public CheckOutPage goToCheckout() {
		return new CheckOutPage(driver);
	}
}
