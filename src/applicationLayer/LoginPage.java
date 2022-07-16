package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
//define Repository
	@FindBy(xpath="//input[@id='txtUsername']")
	WebElement ObjUser;
	@FindBy(name ="txtPassword")
	WebElement ObjPass;
	@FindBy(xpath="//input[@id='btnLogin']")
	WebElement ObjLoginbtn;
	//method for login
	public void verifyLogin(String username,String password)throws Throwable
	{
		ObjUser.sendKeys(username);
		ObjPass.sendKeys(password);
		ObjLoginbtn.click();
		Thread.sleep(2000);
	}
	
}
