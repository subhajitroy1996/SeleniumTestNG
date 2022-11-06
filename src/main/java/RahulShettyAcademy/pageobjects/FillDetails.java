package RahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class FillDetails extends AbstractComponent {
WebDriver driver;
	
	public FillDetails(WebDriver driver)
	{super(driver);
		this.driver=driver;
	}
	
	By country=By.xpath("//input[@placeholder='Select Country']");
	By options=By.cssSelector(".ng-star-inserted span");
	By selected=By.xpath("//span[text()=' India']");
	By confirm=By.xpath("//a[text()='Place Order ']");
	
	public void setCountry(String name)
	{Actions a=new Actions(driver);
	a.sendKeys(driver.findElement(country),name).build().perform();
	
	WaitForElement(options);
	a.moveToElement(driver.findElement(selected)).click().build().perform();}
	
	public Confirmation Confirm()
	{
	driver.findElement(confirm).click();
	Confirmation c=new Confirmation(driver);
	return c;
	}
	
	
}
