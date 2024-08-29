package com.scompany.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.scompany.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
		
	@FindBy(css="tr td:nth-child(3)")
	List <WebElement> orderItemName;
	
	
	public boolean verifyOrderDisplay(String itemName) {
		Boolean match = orderItemName.stream().anyMatch(cartItem->cartItem.getText().equals(itemName));
		return match;
	}
	
	
}
