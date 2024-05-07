package Framework.Design1;
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Design1pageobjects.CartPage;
import Design1pageobjects.Landingpage;
import Design1pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		Landingpage lp = new Landingpage(driver);
		lp.goTo();
		lp.loginApplication("dsf@perv.com", "Qwerty123");
		
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCart();	
		
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		
		boolean match =cartProducts.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(productName))  ;
		Assert.assertTrue(match);
		
		Actions a = new Actions(driver);
		a.scrollToElement(driver.findElement(By.cssSelector(".totalRow button")));
		
	//	w1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
		WebElement checkoutbtn = driver.findElement(By.cssSelector(".totalRow button"));
		checkoutbtn.click();
		
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"ind").build().perform();
		
		//w1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		
		String successmsg = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", successmsg);
		
		
		

	
	}

}
