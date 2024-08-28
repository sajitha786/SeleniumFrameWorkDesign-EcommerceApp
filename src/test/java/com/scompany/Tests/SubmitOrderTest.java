package com.scompany.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.scompany.TestComponents.BaseTest;
import com.scompany.pageobjects.CartPage;
import com.scompany.pageobjects.CheckoutPage;
import com.scompany.pageobjects.ConfirmationPage;
import com.scompany.pageobjects.OrderPage;
import com.scompany.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String itemName = "ZARA COAT 3";
	
	@Test(dataProvider= "getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		String countryName = "uni";
		// Login from the landing page
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		// Getting item list
		List<WebElement> items = productCatalogue.getItemList();

		// retrieving itemName from the list to a variable
		// waiting till the toast message is visible
		// waiting for the refresh animation to be invisible
		// item added to cart
		productCatalogue.addProductToCart(input.get("itemName"));

		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("itemName"));
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
	
	
	//Extended Report- 
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+
				"//src//test//java//com//scompany//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	//@DataProvider
	//public Object[][] getData() {		
	//		return new Object[][] {{"saj@gmail.com","Sheza@123","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	//}
	
//	HashMap<String,String> map =new HashMap<String,String>();
//	map.put(;
//	
//	HashMap<String,String> map1 =new HashMap<String,String>();
//	map1.put("email", "anshika@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("itemName", "ADIDAS ORIGINAL");
}

