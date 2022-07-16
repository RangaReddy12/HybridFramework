package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Logoutpage {
@FindBy(id="welcome")
WebElement welcome;
@FindBy(linkText="Logout")
WebElement Objlogout;
public void verifylogout()throws Throwable
{
	welcome.click();
	Thread.sleep(2000);
	Objlogout.click();
}
}
