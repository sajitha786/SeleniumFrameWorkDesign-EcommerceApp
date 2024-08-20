package com.scompany.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.scompany.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{

WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement OrderConfirmationTitle;
	
	public String getConfirmationMessage() {
		String confirmMessage= OrderConfirmationTitle.getText();
		return confirmMessage;
		}
}
