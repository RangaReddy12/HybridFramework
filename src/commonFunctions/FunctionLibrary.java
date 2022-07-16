package commonFunctions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil {
	//method for login
	public static boolean verifyLogin(String username,String password)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("Objpass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("ObjLogin"))).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String expected ="adminflow";
		String actual = driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("Login success::"+expected+"     "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Fail::"+expected+"     "+actual,true);
			return false;
		}
	}
//method for click branches
	public static void clickBranches()throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjBranches"))).click();
		Thread.sleep(3000);
	}
	//method for logout
	public static boolean verifyLogout()
	{
		driver.findElement(By.xpath(config.getProperty("ObjLogout"))).click();
		if(driver.findElement(By.xpath(config.getProperty("ObjLogin"))).isDisplayed())
		{
			Reporter.log("Logout success",true);
			return true;
		}
		else
		{
			Reporter.log("Logout Fail",true);
			return false;
		}
	}
	public static boolean verifyBranchCreation(String branchname,String Address1,String address2,String Adress3,String Area,String ZipCode,String Country,String State,
			String City)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjNewBranch"))).click();
		driver.findElement(By.xpath(config.getProperty("ObjBranchName"))).sendKeys(branchname);
		driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(Address1);
		driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(address2);
		driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(Adress3);
		driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(Area);
		driver.findElement(By.xpath(config.getProperty("Objzipcode"))).sendKeys(ZipCode);
		new Select(driver.findElement(By.xpath(config.getProperty("Objcountry")))).selectByVisibleText(Country);
		new Select(driver.findElement(By.xpath(config.getProperty("Objstate")))).selectByVisibleText(State);
		new Select(driver.findElement(By.xpath(config.getProperty("Objcity")))).selectByVisibleText(City);
		driver.findElement(By.xpath(config.getProperty("Objsubmit"))).click();
		Thread.sleep(3000);
		//capture alertmesage
		String expectedalertbranch =driver.switchTo().alert().getText();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		String actualalertbranch ="New Branch with id";
		if(expectedalertbranch.toLowerCase().contains(actualalertbranch.toLowerCase()))
		{
			Reporter.log(expectedalertbranch,true);
			return true;
		}
		else
		{
			Reporter.log("Branch Creation Fail",true);
			return false;
		}
		}
	public static boolean verifyBranchUpdate(String branch,String address,String Area)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath(config.getProperty("ObjBranch")));
		element.clear();
		element.sendKeys(branch);
		WebElement element1 = driver.findElement(By.xpath(config.getProperty("ObjAddress")));
		element1.clear();
		element1.sendKeys(address);
		WebElement element2 = driver.findElement(By.xpath(config.getProperty("ObjAreaupdate")));
		element2.clear();
		element2.sendKeys(Area);
		driver.findElement(By.xpath(config.getProperty("ObjUpdate"))).click();
		Thread.sleep(3000);
		String expectedbupdate =driver.switchTo().alert().getText();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		String actualBupdate ="Branch updated ";
		if(expectedbupdate.toLowerCase().contains(actualBupdate.toLowerCase()))
		{
			Reporter.log(expectedbupdate,true);
			return true;
		}
		else
		{
			Reporter.log("Branch update Fails",true); 
			return false;
			}
	}
	public static String generateDate()
	{
		Date date = new Date();
		DateFormat  df= new SimpleDateFormat("YYYY_MM_dd");
		return df.format(date);
	}
}





























