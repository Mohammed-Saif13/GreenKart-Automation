package Design1pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Design1.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastmsg = By.xpath("//div[@id='toast-container']");
	
	public List<WebElement> getProductsList() 
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName) 
	{
		WebElement prod = products.stream().filter(product ->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		return prod;
		
	}

	public CartPage addProductToCart(String productName ) throws InterruptedException
	{
		WebElement prod = getProductByName(productName) ;
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastmsg);
		waitForElementToDisAppear(spinner);
		 CartPage cp = new CartPage(driver);
		 return cp;
	}
}
