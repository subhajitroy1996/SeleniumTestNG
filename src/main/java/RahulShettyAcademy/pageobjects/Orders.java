package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;



public class Orders extends AbstractComponent{

	WebDriver driver;

	public Orders(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
By orders=By.xpath("//tr/td[2]");



public List<WebElement> ordersList()
{return driver.findElements(orders);
	}

public boolean verifyadded(String name)
{List<WebElement> lo=ordersList();

 return lo.stream().anyMatch(a->a.getText().contains(name.toLowerCase()));
	}

public ProductCatelogue goHome()
{return goToHome();
	}


}
