package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement myaccount;
	@FindBy(xpath="(//a[@class='list-group-item'][normalize-space()='Logout'])[1]")
	WebElement logout;

	public boolean myAccountStatus() {
		try {
		return (myaccount.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
		}
	
	public void clickLogout() {
		logout.click();
	}
}
