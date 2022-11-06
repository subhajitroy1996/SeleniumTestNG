package RahulShettyAcademy.test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RahulShettyAcademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name="ZARA";
		WebDriverManager.chromedriver().setup();//replacing System.setProperty, chromedriver gets downloaded auto based on chromedriver version
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wa=new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
		driver.get("https://rahulshettyacademy.com/client/");
		
		LandingPage l=new LandingPage(driver);
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("subhajit1996@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Subhajit@1996.");
		driver.findElement(By.cssSelector("#login")).click();
		
		List<WebElement> w=driver.findElements(By.xpath("//h5/b"));
//		w.stream().filter(a->a.getText().contains("ZARA")).map(a->add(a)).forEach(a->System.out.println(a));
		
		for(WebElement i:w)
		{
			if(i.getText().contains(name))
			{
				i.findElement(By.xpath("parent::h5/following-sibling::button[2]")).click();
		
			}
		}
		wa.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wa.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.ngx-spinner-overlay"))));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cart=driver.findElements(By.cssSelector(".cartSection h3"));
		
		Assert.assertTrue(cart.stream().anyMatch(a->a.getText().toUpperCase().contains(name)));
		
		driver.findElement(By.cssSelector(".subtotal button")).click();
		
//		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "ind").build().perform();
		
		wa.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-star-inserted span")));
//		a.moveToElement(driver.findElement(By.xpath("//input[@placeholder='Select Country']"))).sendKeys("ind").click(driver.findElement(By.xpath("//span[text()=' India']"))).build().perform();
	
		a.moveToElement(driver.findElement(By.xpath("//span[text()=' India']"))).click().build().perform();
		

		
	
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
	
	String[] id=driver.findElement(By.xpath("//table //label[@class='ng-star-inserted']")).getText().split(" ");
		List<String> ar=Arrays.asList(id);
		System.out.println(ar);
		
		
//		Assert.assertEquals(driver.findElement(By.cssSelector("#toast-container .ng-tns-c4-7")).getText(),"Product Added To Cart");
	}
//public static String add(WebElement a)
//{a.findElement(By.xpath("parent::h5/following-sibling::button[2]")).click();
//	return a.getText();
//	}
}
