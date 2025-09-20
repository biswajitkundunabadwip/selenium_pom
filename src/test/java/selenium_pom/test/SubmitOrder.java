package selenium_pom.test;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium_pom.pages.CartPage;
import selenium_pom.pages.CheckoutPage;
import selenium_pom.pages.ConfirmationPage;
import selenium_pom.pages.OrderPage;
import selenium_pom.pages.ProductCatalogue;
import testcomponenets.BaseTest;



public class SubmitOrder extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String>input) throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("username"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
//	@Test(dependsOnMethods= {"submitOrder"})
//	public void OrderHistoryTest()
//	{
//		//"ZARA COAT 3";
//		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
//		OrderPage ordersPage = productCatalogue.goToOrdersPage();
//		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
//		
//}
	@DataProvider
	public Object [][] getData() {
		HashMap<Object, Object> hashMap = new HashMap<>();
		hashMap.put("username", "anshika@gmail.com");
		hashMap.put("password", "Welcome@123");
		
		return new Object[][] {{hashMap}};
	}
}
