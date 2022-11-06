package RahulShettyAcademy.test;

import java.io.IOException;
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
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.TestComponents.Retry;
import RahulShettyAcademy.pageobjects.Cart;
import RahulShettyAcademy.pageobjects.FillDetails;
import RahulShettyAcademy.pageobjects.LandingPage;
import RahulShettyAcademy.pageobjects.ProductCatelogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest{

		@Test(retryAnalyzer=Retry.class)//for the 1st time it wont fail the test, rather it skips, then again when it executes and fails then it reports failure.
		public void ErrorValidation() throws IOException {
//		String name="ZARA";
		
		
		
		l.login("subhajit1996@gmail.com", "Subhajit@1996");
		String msg=l.getErrorMsg();
		System.out.println(msg);
		Assert.assertEquals("Incorrect email or password", msg);
	
		

	}

}
