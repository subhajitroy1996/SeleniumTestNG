package RahulShettyAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RahulShettyAcademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
 public WebDriver driver;
 public LandingPage l;
	public void InitializeDriver() throws IOException
	{
	Properties prop=new Properties();
	FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\RahulShettyAcademy\\resources\\GlobalData.properties");
	prop.load(fis);
	String bro=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser"); //using ternary operator, if no browser value is passed through maven then it takes the default value in .properties
	System.out.println(bro);
	
	if(bro.contains("chrome"))
	{WebDriverManager.chromedriver().setup();//replacing System.setProperty, chromedriver gets downloaded auto based on chromedriver version
	ChromeOptions options=new ChromeOptions();
	if(bro.contains("headless"))
	{
	options.addArguments("headless");//makes the chromedriver run in headless mode
	}
	 driver=new ChromeDriver(options);
	 driver.manage().window().setSize(new Dimension(1920,1080) ); //maximizing the driver to full screen

	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
//	return driver;
	}
	public List<HashMap<String, String>> getJsonToMap(String filepath) throws IOException
	{	//read json to String
		String sdata=FileUtils.readFileToString(new File(filepath),
				StandardCharsets.UTF_8);//giving encoding on how to convert into a string
		
		//String to hashmap
		
	ObjectMapper m=new 	ObjectMapper();//creating object of the class which can readValue from String and convert to a HashMap
	List<HashMap<String,String>> jsondata=m.readValue(sdata,new TypeReference<List<HashMap<String,String>>>(){});
	return jsondata;
	}
	public Object[][] getExcelData(String path) throws IOException
	{	DataFormatter formatter=new DataFormatter();
		FileInputStream fi=new FileInputStream(path);
		XSSFWorkbook workbook=new XSSFWorkbook(fi);
		int count=workbook.getNumberOfSheets();
		int rowcount=workbook.getSheetAt(0).getPhysicalNumberOfRows();
		
		int cellcount=workbook.getSheetAt(0).getRow(0).getLastCellNum();
		System.out.println(rowcount);
		
		Object[][] Data=new Object[rowcount-1][cellcount-1];
		for(int i=0;i<count;i++)
		{if(workbook.getSheetName(i).equals("Details"))
			{
			XSSFSheet sheet=workbook.getSheetAt(i);
			
			for(int j=0;j<rowcount-1;j++)
			{Row r=sheet.getRow(j+1);		//Row or XSSFRow
				for(int k=1;k<cellcount;k++)
				{
					Cell c=r.getCell(k);	//Cell or XSSFCell
//					System.out.println(c.getStringCellValue());
				Data[j][k-1]=formatter.formatCellValue(c);//converts any type of data present in cell(Number,STring) and converts it to String.
				}
			}
			

		}
		
		
	}
//		System.out.println(Data);
		return Data;	
	}
	public String getScreenshot(String testcase,WebDriver driver) throws IOException
	{
		TakesScreenshot ss=	(TakesScreenshot)driver;
		File src=ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//screenshots//"+testcase+".png"));
		return System.getProperty("user.dir")+"//screenshots//"+testcase+".png";
		
	}
	@BeforeMethod(alwaysRun=true)
	public void launch() throws IOException
	
	{
		InitializeDriver();
		l=new LandingPage(driver);
		l.open();
//		return l;
	}
@AfterMethod(enabled=true)
public void teardown()
{driver.close();
	}
}
