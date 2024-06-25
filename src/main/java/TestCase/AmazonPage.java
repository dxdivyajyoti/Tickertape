package TestCase;

import PageObject.AmazonPageRepo;
import Utility.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AmazonPage extends BaseClass
{
    public WebDriver driver;
    AmazonPageRepo amazonHomePage = new AmazonPageRepo(driver);
    BaseClass bs= new BaseClass();
   @BeforeTest
    public  void runBrowser()
   {
       bs.runBrowser("chrome");

   }
   @Test
  public  void testcase()
   {
       String expected = bs.runTest(driver);
       Assert.assertEquals("expected","Apple iPhone");
   }
}
