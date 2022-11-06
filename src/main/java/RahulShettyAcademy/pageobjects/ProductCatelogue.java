package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class ProductCatelogue extends AbstractComponent {

	WebDriver driver;
	public ProductCatelogue(WebDriver driver)
	{super(driver);
		this.driver=driver;
		
	}
	By e=By.xpath("//h5/b");
//	By head=By.xpath("//h5");
	By pname=By.xpath("parent::h5/following-sibling::button[2]");
	By message=By.cssSelector("#toast-container");
	By screen=By.cssSelector("div.ngx-spinner-overlay");
	
	
	public List<WebElement> getProductList()
	{WaitForElement(e);
	return	driver.findElements(e);
	}
	public WebElement getProd(String name)
	{
		List<WebElement> w=getProductList();
		return w.stream().filter(a->a.getText().contains(name)).findFirst().orElse(null);
	}
	
	public void addToCart(String name)
	{WebElement c=getProd(name);
//	System.out.println(driver.findElement(head).getText());
	c.findElement(pname).click();
	WaitForElement(message);
	WaitForElementtoDisapper(screen);
	}
	
public Cart Cart()
{return goToCart();

	}

public Orders goToOrders()
{
 return	goToOrder();
}
}
