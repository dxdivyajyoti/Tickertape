package TestCase;


import java.util.Map;
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

import PageObject.FlipkartPage;
import Utility.FileUtility;

public class TickerTapeAssignmentOne
{
public static WebDriver driver;
public static WebDriverWait wait;
public static FileUtility flib;
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
		chromeOptions.addArguments("--kiosk");
		driver = new ChromeDriver(chromeOptions);

		
	}
	
	driver.get(flib.getValue("flkUrl"));
	//driver.manage().window().maximize();

}
@Test
public void runScenarioOne() 
{
	FlipkartPage fp=new FlipkartPage(driver);
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	fp.closePopup().click();
	try 
	{
		fp.enterProduct().sendKeys(flib.getValue("iteam"));
	} catch (Throwable e)
	{
		
		e.printStackTrace();
	}
	fp.selectIteam().click();
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
