package RahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	By useremail=By.cssSelector("#userEmail");
	By userpass=By.cssSelector("#userPassword");
	By login=By.cssSelector("#login");
	By error=By.cssSelector("#toast-container");
	
	
	public ProductCatelogue login(String email,String pass)
	{
//		try {
		driver.findElement(useremail).sendKeys(email);
		driver.findElement(userpass).sendKeys(pass);
		driver.findElement(login).click();
		
//		}
//	catch(Exception e)
//	{
//		Logger log=Logger.getLogger(getClass().getMethod(getErrorMsg(), null));
//	}
	ProductCatelogue p=new ProductCatelogue(driver);
	return p;
	}
	
	
	
	public void open()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	public String getErrorMsg()
	{	WaitForElement(error);
		return driver.findElement(error).getText();
	}
}
