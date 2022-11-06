package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class Cart {

	WebDriver driver;

	public Cart(WebDriver driver) {
		this.driver = driver;
	}
By items=By.cssSelector(".cartSection h3");

By checkout=By.cssSelector(".subtotal button");

public List<WebElement> cartElements()
{return driver.findElements(items);
	}

public boolean check(String name)
{List<WebElement> li=cartElements();

 return li.stream().anyMatch(a->a.getText().contains(name));
	}

public FillDetails checkout()
{


	driver.findElement(checkout).click();

	FillDetails f=new FillDetails(driver);
	return f;
	}
}
