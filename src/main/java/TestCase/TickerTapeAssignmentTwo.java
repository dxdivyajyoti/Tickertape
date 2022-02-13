package TestCase;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObject.AmazonPage;
import PageObject.FlipkartPage;
import Utility.FileUtility;

public class TickerTapeAssignmentTwo 
{
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static FileUtility flib;
	@BeforeMethod
	public  void initConfiguration() 
	{
		
		try {
			if(flib.getValue("browser").equals("firefox"))
			{
				
				driver = new FirefoxDriver();
				
			}
			else if(flib.getValue("browser").equals("chrome"))
			{
				
				System.setProperty("webdriver.chrome.driver", "/Users/divyajyoti/selenium/chromedriver");

			
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				options.addArguments("start-Maximized");

				driver = new ChromeDriver(options);
			}
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try 
		{
			driver.get(flib.getValue("flkUrl"));
		} 
		catch (Throwable e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().window().maximize();

	}
	@Test(enabled = false)
	public void fkpScenario() 
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
		
		System.out.println(fp.pricePrint().getText());
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
	System.out.println(fp.getPrice().getText());
	
	String flpPrice = fp.getPrice().getText();
	
    String productName = fp.getName().getText();

	System.out.println("product name is "+fp.getName().getText());
	}
	@Test(priority=1)
	public void amzScenario() throws Throwable
	{
		
	// Launch Amazon url
		driver.get(flib.getValue("amzUrl"));
		wait=new WebDriverWait(driver,20);
	// Wait for the pop-up to close
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='twotabsearchtextbox']")));

	AmazonPage amz=new AmazonPage(driver);
	
	amz.amzSearch().sendKeys(flib.getValue("iteam2"));
	
	amz.match().click();
	String parent = driver.getWindowHandle();
	
	amz.clickOnIteam().click();
	
	Set<String> tabs = driver.getWindowHandles();
	for(String tab:tabs)
	{
		if(!tab.equalsIgnoreCase(parent))
		{
			driver.switchTo().window(tab);
		}
	}
	
	amz.addToCart().click();
     wait.until(ExpectedConditions.visibilityOf(amz.goToCart));
	
	amz.gotoCart().click();
	
	
	
	
	
	
	}
	
//	@AfterMethod
//	public static void quitBrowser()
//	{
//		
//		driver.quit();
//		
//	}
}
