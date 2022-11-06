package RahulShettyAcademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
public static ExtentReports getReportObject()
{String path=System.getProperty("user.dir")+"\\report\\index.html";
ExtentSparkReporter reporter=new ExtentSparkReporter(path);
reporter.config().setReportName("Title Check");
reporter.config().setDocumentTitle("Extent Report");

ExtentReports report=new ExtentReports();
report.attachReporter(reporter);
report.setSystemInfo("Tester", "RahulShetty");

return report;
	}
}
