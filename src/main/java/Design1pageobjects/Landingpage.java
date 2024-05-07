package Design1pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Design1.AbstractComponents.AbstractComponent;

public class Landingpage extends AbstractComponent {

	WebDriver driver;
	
	public Landingpage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userpwd;
		
	//	driver.findElement(By.xpath("//input[@id='login']")).click();
	@FindBy(id="login")
	WebElement submit;
	
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public ProductCatalogue loginApplication(String email , String pwd)
	{
		userEmail.sendKeys(email);
		userpwd.sendKeys(pwd);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

}
