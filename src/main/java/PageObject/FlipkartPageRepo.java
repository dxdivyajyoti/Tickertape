package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.FileUtility;

public class FlipkartPageRepo
{
	
	
	public static FileUtility flib;
public static WebDriver driver;
@FindBy(xpath = "//button[@class='_2KpZ6l _2doB4z']") public WebElement closePopup;
@FindBy(xpath ="//input[@placeholder='Search for products, brands and more']" ) public WebElement searchBox;
@FindBy(xpath = "//button[@class='L0Z3Pu']")  public WebElement dropdownResult;
@FindBy(xpath = "(//div[@class='_13oc-S'])[1]")  public WebElement firstElement;
@FindBy(xpath="//div[@class='_25b18c']/descendant::div[@class='_30jeq3 _16Jk6d']")
public WebElement initialPrice;
@FindBy(xpath = "//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")  public WebElement addToCart;
@FindBy(xpath = "(//div[@class='_2uHLC8 llHQRh _1o7oHE'])[2]") public WebElement scroll;
@FindBy(xpath = "(//button[@class='_23FHuj'])[2]")  public WebElement updateQuantity;
@FindBy(xpath = "//div[@class='Ob17DV _3X7Jj1']")  public WebElement getPrice;
@FindBy(xpath="//div[@class='_3fSRat']/descendant::div[@class='_2-uG6-']") public WebElement getName;

public FlipkartPageRepo(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver,this);
}
public WebElement pricePrint()
{
	return initialPrice;
}
public WebElement closePopup()
{
	return closePopup;
}
public WebElement scrollAction()
{
	return scroll;
}
public WebElement enterProduct() throws Throwable
{
	return searchBox;
}
public WebElement  selectIteam()
{
	return dropdownResult;
}
public WebElement selectFirst()
{
	return firstElement;
}
public WebElement  addingToCart()
{
	return addToCart;
}
public WebElement addOneMore()
{
	return updateQuantity;
}
public WebElement  getPrice()
{
	return getPrice;
}
public WebElement getName()
{
	return getName;
}


}
