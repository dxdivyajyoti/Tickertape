package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestOne
{
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","/Users/b0276648/Desktop/IQPT/Tickertape/chromedriver");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.amazon.com/");
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("(//span[@class='a-button-inner'])[1]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("iphone");
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
        String test=driver.findElement(By.xpath("(//div[@class='a-section a-spacing-none puis-padding-right-small s-title-instructions-style'])[1]")).getText();
        System.out.println(test);


    }
}
