package com.scompany.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.scompany.pageobjects.CartPage;
import com.scompany.pageobjects.CheckoutPage;
import com.scompany.pageobjects.ConfirmationPage;
import com.scompany.pageobjects.OrderPage;
import com.scompany.pageobjects.ProductCatalogue;
import com.scompny.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String itemName = "ZARA COAT 3";
	
	@Test
	public void submitOrder() throws IOException {
		// TODO Auto-generated method stub
		String countryName = "uni";
		// Login from the landing page
		ProductCatalogue productCatalogue = landingPage.loginApplication("saj@gmail.com", "Sheza@123");
		// Getting item list
		List<WebElement> items = productCatalogue.getItemList();

		// retrieving itemName from the list to a variable
		// waiting till the toast message is visible
		// waiting for the refresh animation to be invisible
		// item added to cart
		productCatalogue.addProductToCart(itemName);

		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(itemName);
		Assert.assertTrue(match);

		CheckoutPage chkoutPage = cartPage.goToCheckout();
		chkoutPage.select_Country(countryName);

		ConfirmationPage confirmationPage = chkoutPage.submitOrder();
		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("Thankyou for the order."));
		}

	@Test (dependsOnMethods={"submitOrder"})
	public void orderHistory() {
		//To verify ZARA COAT 3 is displaying in orders page  
		ProductCatalogue productCatalogue = landingPage.loginApplication("saj@gmail.com", "Sheza@123");
		OrderPage orderPage= productCatalogue.goToOrderPage();
		Boolean match = orderPage.verifyOrderDisplay(itemName);
		Assert.assertTrue(match);
	}
}
