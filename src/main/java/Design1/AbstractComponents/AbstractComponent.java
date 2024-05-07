package Design1.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v120.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Design1pageobjects.CartPage;

public class AbstractComponent
{
	WebDriver driver;

	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver; 
		PageFactory.initElements(driver, this);
	} 
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait w1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		w1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	public void waitForElementToDisAppear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(2000);
		WebDriverWait w1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		w1.until(ExpectedConditions.invisibilityOf(ele));
	}
	public CartPage goToCart()
	{
		cartButton.click();
		return null;
		
	}
}
