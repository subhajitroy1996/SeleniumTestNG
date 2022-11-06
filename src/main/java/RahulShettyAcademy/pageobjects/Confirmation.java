package RahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import RahulShettyAcademy.AbstractComponents.AbstractComponent;

public class Confirmation extends AbstractComponent{
	
	WebDriver driver;
	
	public Confirmation(WebDriver driver)
	{super(driver);
		this.driver=driver;
	}
	
By msg=By.xpath("//h1");

public String getmsg()
{return driver.findElement(msg).getText();
	}

public LandingPage SigningOut()
{return Signout();
	}
}
