package RahulShettyAcademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import RahulShettyAcademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest  implements ITestListener {
ExtentTest test;
ExtentReports reports=ExtentReporterNG.getReportObject();
ThreadLocal<ExtentTest> testthread=new ThreadLocal<ExtentTest>();//even if we are running concurrently, each object creation will have its own thread, it wont interrupt other overriding variable
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		 
		System.out.println(result.getMethod().getMethodName());
		
		test=reports.createTest(result.getMethod().getMethodName());
		testthread.set(test);//unique thread id(ErrorValidation)->maps it to the thread object
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		testthread.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		testthread.get().fail(result.getThrowable());//simply taking the console result
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());//field is present at class level
		}	
		 catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String path = null;
		try {
			path=getScreenshot(result.getMethod().getMethodName(),driver);
			System.out.println(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//if no screenshot exists,then it invokes the method
		}
		testthread.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());//testthread.get() will get the exact thread which was created by errorvalidation
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		reports.flush();
	}

}
