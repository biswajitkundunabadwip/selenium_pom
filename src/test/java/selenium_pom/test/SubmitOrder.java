package selenium_pom.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import selenium_pom.pages.CartPage;
import selenium_pom.pages.CheckoutPage;
import selenium_pom.pages.ConfirmationPage;
import selenium_pom.pages.OrderPage;
import selenium_pom.pages.ProductCatalogue;
import testcomponenets.BaseTest;



public class SubmitOrder extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test
	public void submitOrder() throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication("biswajit.kundu9@gmail.com", "Welcome@123");
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
}
