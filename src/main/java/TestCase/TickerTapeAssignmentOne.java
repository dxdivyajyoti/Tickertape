package TestCase;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObject.FlipkartPageRepo;
import Utility.FileUtility;

public class TickerTapeAssignmentOne
{
public static WebDriver driver;
public static WebDriverWait wait;
public static FileUtility flib;

//Launch browser  with required Browser name

@BeforeMethod
public  void initConfiguration() throws Throwable
{
	
	if(flib.getValue("browser").equals("firefox"))
	{
		
		driver = new FirefoxDriver();
		
	}
	else if(flib.getValue("browser").equals("chrome"))
	{
		
		System.setProperty("webdriver.chrome.driver", "/Users/divyajyoti/selenium/chromedriver");

	
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-fullscreen");
		driver = new ChromeDriver(chromeOptions);

		
	}
	// url to be fetched from Property file using custom function from Utility Package
	driver.get(flib.getValue("flkUrl"));
	//driver.manage().window().maximize();

}
@Test
public void fkScenarioOne() 
{
	
	FlipkartPageRepo fp=new FlipkartPageRepo(driver);
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	fp.closePopup().click();
	try 
	{
		
		// product name to be fetched from Property file by custom function defined on utility
		
		fp.enterProduct().sendKeys(flib.getValue("iteam"));
	} catch (Throwable e)
	{
		
		e.printStackTrace();
	}
	//select the first iteam shown
	
	fp.selectIteam().click();
	
	// getting windowhandle id to switching control from current to sub sequent tabs
	
	String parent=driver.getWindowHandle();
	fp.selectFirst().click();
	Set<String> tabs = driver.getWindowHandles();
	for(String tab:tabs)
	{
		if(!tab.equalsIgnoreCase(parent))
		{
			driver.switchTo().window(tab);
		}
	}
	System.out.println("Selling price  @Flipkart is "+fp.pricePrint().getText());
	System.out.println("-----------------------------------------------");
	fp.addingToCart().click();
	
	//Scroll down to avoid Exception
	
	
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView();",fp.scrollAction());
	
fp.addOneMore().click();
try {
	Thread.sleep(3000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

String flpPrice = fp.getPrice().getText();
System.out.println("Selling price of Product with Qty 2 is  "+flpPrice);
System.out.println("-----------------------------------------------");

System.out.println("product name is"+fp.getName().getText());
}
@AfterMethod
public static void quitBrowser()
{
	
	driver.quit();
	
}

}
