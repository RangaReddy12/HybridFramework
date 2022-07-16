package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import applicationLayer.AddEmpPage;
import applicationLayer.LoginPage;
import applicationLayer.Logoutpage;
import utilites.ExcelFileUtil;

public class POMDriverScripts {
WebDriver driver;
String inputpath ="D:\\Selenium_9oclockBatch\\Hybrid_Framework\\TestInput\\EmpData.xlsx";
String outputpath ="D:\\Selenium_9oclockBatch\\Hybrid_Framework\\TestOutput\\DataDrivenPom.xlsx";
ExtentReports report ;
ExtentTest test;
@BeforeTest
public void adminLogin()throws Throwable
{
	System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
	report = new ExtentReports("./Reports/PomDatadriven.html");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
	
}
@Test
public void empCreation()throws Throwable
{
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	int rc =xl.rowCount("EmpData");
	Reporter.log("No of rows are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		test= report.startTest("Validate Emp");
		String para1 =xl.getCellData("EmpData", i, 0);
		String para2 =xl.getCellData("EmpData", i, 1);
		String para3 =xl.getCellData("EmpData", i, 2);
		AddEmpPage emp =PageFactory.initElements(driver, AddEmpPage.class);
		boolean res=emp.verifyemp(para1, para2, para3);
		if(res)
		{
			xl.setCellData("EmpData", i, 3, "Pass", outputpath);
			test.log(LogStatus.PASS, "Emp creation success");
		}
		else
		{
			xl.setCellData("EmpData", i, 3, "Fail", outputpath);
			test.log(LogStatus.FAIL, "Emp Creation Fail");
		}
	}
}
@AfterTest
public void logoutapp()throws Throwable
{
	report.endTest(test);
	report.flush();
	Logoutpage logout =PageFactory.initElements(driver, Logoutpage.class);
	logout.verifylogout();
	driver.close();
}
}













