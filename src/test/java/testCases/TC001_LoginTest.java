package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.TestBase;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC001_LoginTest extends TestBase {
	
	@Test(groups={"Sanity","Master"})
	public void verifyLogin() {
		try {
	logger.info("starting test");
	HomePage hm=new HomePage(driver);
	hm.clickMyAccount();
	hm.clickLogin();
	
	LoginPage lg=new LoginPage(driver);
	lg.setEmail(p.getProperty("email"));
	lg.setPassword(p.getProperty("password"));
	lg.clkLogin();
	
	MyAccountPage myacc=new MyAccountPage(driver);
	boolean status=myacc.myAccountStatus();
	Assert.assertEquals(status, true);
		}
		catch(Exception e) {
			Assert.fail();
		}
	logger.info("test finished");
	
	
	
	}
	
}
