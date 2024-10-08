package com.scompany.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.scompany.pageobjects.CartPage;
import com.scompany.pageobjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;
		
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		 wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		 PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	

	@FindBy(css="[routerlink*='myorders']")
	WebElement orders;
	
	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(WebElement refreshIcon) {
		wait.until(ExpectedConditions.invisibilityOf(refreshIcon));
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		orders.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	public void waitForElementToBeVisible(WebElement drpdwnCountryList) {
		wait.until(ExpectedConditions.visibilityOf(drpdwnCountryList));
	}
}
