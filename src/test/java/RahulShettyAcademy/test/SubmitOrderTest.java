package RahulShettyAcademy.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageobjects.Cart;
import RahulShettyAcademy.pageobjects.Confirmation;
import RahulShettyAcademy.pageobjects.FillDetails;
import RahulShettyAcademy.pageobjects.LandingPage;
import RahulShettyAcademy.pageobjects.Orders;
import RahulShettyAcademy.pageobjects.ProductCatelogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	
		@Test(dataProvider="getDataExcel2")
		public void SubmitOrder(String email,String password,String name) throws IOException {
		
		
		
		
		ProductCatelogue p=l.login( email,password);
		
		p.addToCart(name);
//		String email,String password,String name
		Cart c=p.Cart();
		Assert.assertTrue(c.check(name));
		
		FillDetails f=c.checkout();
		f.setCountry("ind");
		Confirmation co=f.Confirm();
		Assert.assertEquals(co.getmsg(),"THANKYOU FOR THE ORDER.");
		co.SigningOut();
		

	}
//	@Test(dependsOnMethods= {"SubmitOrder"},dataProvider="getData")	
//	public void OrderHistory(HashMap<String,String> input)
//	{ProductCatelogue p=l.login(input.get("email"), input.get("password"));
//	p.addToCart(input.get("name"));
//	
//	
//	Orders o=p.goToOrders();
////	Assert.assertTrue(input.get("name"));
//	o.goHome();
//	
//	
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//	Object[][] obj=new Object[2][2];
//	obj[0][0]="subhajit1996@gmail.com";
//	obj[0][1]="Subhajit@1996.";
//	obj[1][0]="nirmalapandit@gmail.com";
//	obj[1][1]="Pandit1982";
//	return obj;
//	return new Object[][] {{"subhajit1996@gmail.com","Subhajit@1996.","ZARA"},{"subhajit1996@gmail.com","Subhajit@1996.","ADIDAS"}};//Object data type stores any kind of data(int,string,float)
	
		List<HashMap<String, String>> data= getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\RahulShettyAcademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}


	@DataProvider 
	public Object[][] getDataMap()
	{HashMap<String,String> map=new HashMap<String,String>();
	map.put("email", "subhajit1996@gmail.com");
	map.put("password", "Subhajit@1996.");
	map.put("name", "ZARA");
	
	HashMap<String,String> map1=new HashMap<String,String>();
	map1.put("email", "subhajit1996@gmail.com");
	map1.put("password", "Subhajit@1996.");
	map1.put("name", "ADIDAS");
	
	return new Object[][] {{map},{map1}};// each {} represents a row and the outer {} the multi dimensional array
	}
	@DataProvider
	public Object[][] getDataExcel2() throws IOException
	{
		
		
//		Object[][] data= {{ar}};
		
		return getExcelData("C:\\Users\\Subhojit\\Desktop\\Pandit.xlsx");
		

		
		
	}
	
}
