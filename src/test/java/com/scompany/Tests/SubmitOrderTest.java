package com.scompany.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.scompany.pageobjects.CartPage;
import com.scompany.pageobjects.ConfirmationPage;
import com.scompany.pageobjects.LandingPage;
import com.scompany.pageobjects.ProductCatalogue;
import com.scompany.pageobjects.checkoutPage;
import com.scompny.TestComponents.BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;



public class SubmitOrderTest extends BaseTest{

	@Test
	public void submitOrder() throws IOException {
		// TODO Auto-generated method stub
		
		String itemName= "ZARA COAT 3";
		String countryName="uni";
		
		//Login from the landing page
		LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue=landingPage.loginApplication("saj@gmail.com", "Sheza@123");
		
		//Getting item list
		List<WebElement> items = productCatalogue.getItemList();
		
		//retrieving itemName from the list to a variable
		//waiting till the toast message is visible
		//waiting for the refresh animation to be invisible
		//item added to cart
		productCatalogue.addProductToCart(itemName);
		
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match= cartPage.verifyProductDisplay(itemName);
		Assert.assertTrue(match);
		
		checkoutPage chkoutPage = cartPage.goToCheckout();
		chkoutPage.select_Country(countryName);
		
		ConfirmationPage confirmationPage =chkoutPage.submitOrder();
		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("Thankyou for the order."));
		cartPage.closeTheBrowser();
	}

}
