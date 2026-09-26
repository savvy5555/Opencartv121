package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement fstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lstName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement telephone;
	
	@FindBy(xpath="(//input[@id='input-password'])[1]")
	WebElement pswd;
	
	@FindBy(xpath="(//input[@id='input-confirm'])[1]")
	WebElement cnfrmpswd;
	
	@FindBy(xpath="(//input[@value='Continue'])[1]")
	WebElement conbtn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement title;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement radiobtn;
	public void fname(String fn) {
		fstName.sendKeys(fn);
	}
	
	public void lname(String ln) {
		lstName.sendKeys(ln);
	}
	
	public void entemail(String eid) {
		email.sendKeys(eid);
	}
	
	public void tphone(String tp) {
		telephone.sendKeys(tp);
	}
	
	public void psid(String pid) {
		pswd.sendKeys(pid);
	}
	
	public void cpsid(String cpid) {
		cnfrmpswd.sendKeys(cpid);
	}
	
	public void contibtn() {
		conbtn.click();
	}
	
	public void rd() {
		radiobtn.click();
		
	}
	
	public String getConfirmation() {
		
		try {
			return(title.getText());
		}
		catch(Exception e){
			return(e.getMessage());
			
		}
	}
}
