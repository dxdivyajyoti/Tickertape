package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonPage 
{
	public static WebDriver driver;
	
	@FindBy(xpath = "//input[@id='twotabsearchtextbox']") public WebElement amzSearchBox;
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	public WebElement selectMatch;
	
	@FindBy(xpath="(//div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='a-size-medium a-color-base a-text-normal'])[1]")
	public WebElement clickOnIteam;
	
	@FindBy(xpath="//div[@class='a-section a-spacing-small']/descendant::span[@class='a-price a-text-price a-size-medium apexPriceToPay']") public WebElement initialPrice;
	
	
	@FindBy(xpath="//input[@id='add-to-cart-button']")public WebElement addToCart;
	
	@FindBy(xpath="//span[@id='attach-sidesheet-view-cart-button']")public WebElement goToCart;
	
	@FindBy(xpath="//span[@class='a-button-inner']/descendant::span[@class='a-button-text a-declarative']")public WebElement qtyDropdown;
	
	@FindBy(xpath="//a[@id='quantity_2']") public WebElement addOneMore;
	
	@FindBy(xpath="(//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap'])[1]") public 
	WebElement printPrice;
	
	public AmazonPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	public WebElement amzSearch()
	{
		return amzSearchBox;
	}
	
	
	public WebElement match()
	{
		return selectMatch;
	}
	
	
	public WebElement clickOnIteam()
	{
		return clickOnIteam;
	}
	
	public WebElement initialPrice()
	{
		return initialPrice;
	}
	
	public WebElement addToCart()
	{
		return addToCart;
	}
	
	
	public WebElement gotoCart()
	{
		return goToCart;
	}
	
	
	
	public WebElement Drodown()
	{
		return qtyDropdown;
	}
	
	
	public WebElement addOneMore()
	{
		return addOneMore;
	}
	
	public WebElement printPrice()
	{
		return printPrice;
	}


}
