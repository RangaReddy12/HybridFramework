package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationLayer.AddEmpPage;
import applicationLayer.LoginPage;
import applicationLayer.Logoutpage;

public class TestScript {
WebDriver driver;
@BeforeTest
public void setUp()throws Throwable
{
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	LoginPage  login = PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void validateemp()throws Throwable
{
	AddEmpPage emp =PageFactory.initElements(driver, AddEmpPage.class);
	boolean res =emp.verifyemp("Ranga", "Akhi", "Selenium");
	Reporter.log("Emp Creation::"+res,true);
}
@AfterTest
public void tearDown()throws Throwable
{
	Logoutpage logout = PageFactory.initElements(driver, Logoutpage.class);
	logout.verifylogout();
	driver.close();
}
}



















