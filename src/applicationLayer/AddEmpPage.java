package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddEmpPage {
WebDriver driver;
public AddEmpPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//b[normalize-space()='PIM']")
WebElement Objpim;
@FindBy(name="btnAdd")
WebElement ObjAddBtn;
@FindBy(name="firstName")
WebElement objfname;
@FindBy(name="middleName")
WebElement objmname;
@FindBy(name="lastName")
WebElement objlname;
@FindBy(name="employeeId")
WebElement beforeempid;
@FindBy(id="btnSave")
WebElement objSavebtn;
@FindBy(id="personal_txtEmployeeId")
WebElement afterempid;
public boolean verifyemp(String firstname,String middlename,String lastname)throws Throwable
{
	Actions ac = new Actions(driver);
	ac.moveToElement(Objpim).click().perform();
	Thread.sleep(2000);
	ac.moveToElement(ObjAddBtn).click().perform();
	Thread.sleep(2000);
	this.objfname.sendKeys(firstname);
	this.objmname.sendKeys(middlename);
	this.objlname.sendKeys(lastname);
	String expectedid =this.beforeempid.getAttribute("value");
	this.objSavebtn.click();
	Thread.sleep(3000);
	String actualid =this.afterempid.getAttribute("value");
	if(expectedid.equals(actualid))
	{
		Reporter.log("Emp Creation success"+expectedid+"    "+actualid,true);
		return true;
	}
	else
	{
		Reporter.log("Emp Creation Fail"+expectedid+"    "+actualid,true);
		return false;
	}
	}
}












