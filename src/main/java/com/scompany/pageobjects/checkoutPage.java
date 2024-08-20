package com.scompany.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.scompany.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryTextBox;
	
	
	@FindBy(xpath="//section[contains(@class,'ta-results')]")
	WebElement drpdwnCountryList;

	@FindBy(xpath="//button[contains(@class,'ta-item')][4]")
	WebElement selectCountry;
	
	@FindBy(xpath="//a[contains(@class,'btnn')]")
	WebElement placeOrderButton;
	
	public void select_Country(String countryName) {
		
		checkOutButton.click();
		countryTextBox.sendKeys(countryName);
		waitForElementToBeVisible(drpdwnCountryList);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		placeOrderButton.click();
		return new ConfirmationPage(driver);
	}
}
