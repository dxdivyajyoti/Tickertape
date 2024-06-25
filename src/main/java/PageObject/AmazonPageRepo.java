package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AmazonPageRepo
{
	protected WebDriver driver;

	private By amzSearchBox = By.xpath("//div[@class='nav-search-field ']");
	private By clickonSearch = By.xpath("//input[@id='nav-search-submit-button']");
	private By iteamDetails = By.xpath("(//div[@class='a-section a-spacing-none puis-padding-right-small s-title-instructions-style'])[1]");


	public AmazonPageRepo (WebDriver driver)
	{
		this.driver = driver;
	}

	



	public void enterSearchKeyword(String keyword) {
		WebElement searchBox = driver.findElement(amzSearchBox);
		searchBox.sendKeys(keyword);
	}
	public void clickSearchButton() {
		WebElement searchButton = driver.findElement(clickonSearch);
		searchButton.click();
	}
	public String getFirstItemDetails() {
		WebElement item = driver.findElement(iteamDetails);
		return item.getText();
	}



}
