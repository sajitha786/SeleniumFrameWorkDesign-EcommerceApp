package com.scompany.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.scompany.AbstractComponents.AbstractComponents;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	//WebElement userEmails= driver.findElement(By.id("userEmail"));
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String errorValidation() {
		waitForElementToBeVisible(errorMessage);
		return errorMessage.getText();
	}
}
