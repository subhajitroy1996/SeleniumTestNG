package RahulShettyAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulShettyAcademy.pageobjects.Cart;
import RahulShettyAcademy.pageobjects.LandingPage;
import RahulShettyAcademy.pageobjects.Orders;
import RahulShettyAcademy.pageobjects.ProductCatelogue;

public class AbstractComponent  {

	
	WebDriver driver;
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By cart=By.xpath("//button[@routerlink='/dashboard/cart']");
	By order=By.xpath("//button[@routerlink='/dashboard/myorders']");
	By signout=By.xpath("(//ul/li/button)[4]");
	By home=By.xpath("(//ul/li/button)[1]");
	
	public void WaitForElement(By locator)
	{
	WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(2));
	w.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void WaitForElementtoDisapper(By locator)
	{
	WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
	w.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public Cart goToCart()
	{
		driver.findElement(cart).click();
		Cart c=new Cart(driver);
		return c;
	}
	
	public Orders goToOrder()
	{
		driver.findElement(order).click();;
		Orders o=new Orders(driver);
		return o;
	}
	
	public LandingPage Signout()
	{
		driver.findElement(signout).click();
		LandingPage l= new LandingPage(driver);
		return l;
	}
	
	protected ProductCatelogue goToHome()
	{driver.findElement(home).click();
	ProductCatelogue p=new ProductCatelogue(driver);
	return p;
	}
}
