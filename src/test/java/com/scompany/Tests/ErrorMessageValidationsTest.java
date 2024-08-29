package com.scompany.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.scompany.TestComponents.BaseTest;
import com.scompany.TestComponents.Retry;
import com.scompany.pageobjects.CartPage;
import com.scompany.pageobjects.ProductCatalogue;

public class ErrorMessageValidationsTest extends BaseTest {

	@Test (groups={"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginErrorValidation() {
		// Login from the landing page
		landingPage.loginApplication("saj@gmail.com", "Shezaaaa@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.errorValidation());
		}
	
	@Test
	public void productErrorValidation() {
			// TODO Auto-generated method stub

			String itemName = "ZARA COAT 3";
			ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
			List<WebElement> items = productCatalogue.getItemList();
			productCatalogue.addProductToCart(itemName);
			CartPage cartPage = productCatalogue.goToCartPage();
			Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);
	}
}
