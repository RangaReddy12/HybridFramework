package constant;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {
public static WebDriver driver;
public static Properties config;
@BeforeSuite
public void setUp()throws Throwable
{
	config = new Properties();
	config.load(new FileInputStream("D:\\Selenium_9oclockBatch\\Hybrid_Framework\\PropertyFiles\\Environment.properties"));
	if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(config.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	else if(config.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", "D:/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(config.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	else
	{
		Reporter.log("Browser value is Not matching",true);
	}
}
@AfterSuite
public void tearDown()
{
	driver.close();
}
}
