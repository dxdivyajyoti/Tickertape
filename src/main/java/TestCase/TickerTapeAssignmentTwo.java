package TestCase;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import PageObject.AmazonPageRepo;
import PageObject.FlipkartPageRepo;
import Utility.FileUtility;
public class TickerTapeAssignmentTwo 
{
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static FileUtility flib;
	
	
	//Launch browser  with required Browser name
	
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

			
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--start-fullscreen");
				driver = new ChromeDriver(chromeOptions);
				
			}
		} 
		catch (Throwable e1) 
		{
			
			e1.printStackTrace();
		}
		


	}
	

	
	@Test
	public void fkamzScenario() throws Throwable 
	{
		// url to be fetched from Property file using custom function from Utility Package
		
		driver.get(flib.getValue("flkUrl"));
		
		//object creation of Page class to get elements 
		
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
		
		String parentFkp=driver.getWindowHandle();
		fp.selectFirst().click();
		Set<String> fkptabs = driver.getWindowHandles();
		for(String fkptab:fkptabs)
		{
			if(!fkptab.equalsIgnoreCase(parentFkp))
			{
				driver.switchTo().window(fkptab);
			}
		}
		
		System.out.println("Selling price  @Flipkart is "+fp.pricePrint().getText());
		System.out.println("-----------------------------------------------");
		fp.addingToCart().click();
		
		//Scroll down to avoid Exception
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",fp.scrollAction());
		
	  fp.addOneMore().click();
	  
	try 
	{
		Thread.sleep(3000);
	} 
	catch (InterruptedException e) 
	{
		
		e.printStackTrace();
	}
	String productName = fp.getName().getText();
	
	String flpPrice = fp.getPrice().getText();
	System.out.println("Selling price of Product with Qty 2 is "+flpPrice);
	System.out.println("-----------------------------------------------");
	
	// as Price contains rupee symbol,comma,decimal value 
		// we have to replace and remove these before convert to int type
	 
	String str1=flpPrice.replace("₹", "").replace(",","");

	int fkPrice = Integer.parseInt(str1);
	
	
	
	// Launch Amazon url

	
	driver.get(flib.getValue("amzUrl"));
	
	
//Define Explicit wait
	
     wait=new WebDriverWait(driver,20);
	// Wait for the pop-up to close
		
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='twotabsearchtextbox']")));

	AmazonPageRepo amz=new AmazonPageRepo(driver);
	
	amz.amzSearch().sendKeys(productName);
	
	amz.match().click();
	
	String parentAmz = driver.getWindowHandle();
	
	amz.clickOnIteam().click();

	// Switching to tabs
	Set<String> amztabs = driver.getWindowHandles();
	
	for(String amztab:amztabs)
	{
		if(!amztab.equalsIgnoreCase(parentAmz))
		{
			driver.switchTo().window(amztab);
		}
	}
	
	System.out.println("Selling price  @Amazon is "+amz.initialPrice().getText());
	System.out.println("-----------------------------------------------");
	
	amz.addToCart().click();

	//wait to check element to be visible before Click action.
	
     wait.until(ExpectedConditions.visibilityOf(amz.goToCart));
	
	amz.gotoCart().click();
	
	amz.Drodown().click();
	
	amz.addOneMore().click();
	
	// Static wait is used to wait till update the price after selecting option from dropdown 
	Thread.sleep(2000);

	
	
	String price = amz.printPrice().getText();
	
	System.out.println("Selling price of Product with Qty 2 is "+price);
	System.out.println("-----------------------------------------------");
	
	// as Price contains rupee symbol,comma,decimal value 
	// we have to replace and remove these before convert to int type
	
	String str2=price.replace(",", "").replace(" ","").replace(".00", "");
	
	// to convert String to Primitive int ,use parseInt method of Integer Wrapper class
	
	
	int amzPrice = Integer.parseInt(str2);
	
	
	
	
	//compare the price
	
	
	if(fkPrice>amzPrice)
	{
		System.out.println("amazon is better place to Order ");
	}
	
	else if(fkPrice<amzPrice)
	{
		System.out.println("Flipkart is better to choose");
	}
	
	else
	{
		System.out.println("Both the marketPlace have same price ");
	}
	
}
	
	
	@AfterMethod
	public static void quitBrowser()
	{
		
		driver.quit();
		
	}
}
