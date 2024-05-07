package Design1pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Design1.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	@FindBy(xpath ="//div[@class='cartSection']/h3")
	List<WebElement> cartItems;
	
	
	public boolean VerifyCartItems(String productName)
	{
		boolean match = cartItems.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(productName)) ;
		return match;
	}
	
	public void goToCheckout() 
	{
		checkoutBtn.click();
		new CheckoutPage(driver);
	}
	
	
	
}
