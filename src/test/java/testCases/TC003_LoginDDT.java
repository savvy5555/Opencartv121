package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.TestBase;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends TestBase {
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")
	public void verify_LoginDDT(String email, String pwd, String exp) {
	
	logger.info("starting test");
	try {
	HomePage hm=new HomePage(driver);
	hm.clickMyAccount();
	hm.clickLogin();
	
	LoginPage lg=new LoginPage(driver);
	lg.setEmail(email);
	lg.setPassword(pwd);
	lg.clkLogin();
	
	MyAccountPage myacc=new MyAccountPage(driver);
	boolean status=myacc.myAccountStatus();
	
	if(exp.equalsIgnoreCase("valid")) {
		if(status==true) {
			Assert.assertTrue(true);
			myacc.clickLogout();
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	if(exp.equalsIgnoreCase("invalid")) {
		if(status==true) {
		myacc.clickLogout();
			Assert.assertTrue(false);
		}
		else {
			Assert.assertTrue(true);
		}
	}


}
	catch(Exception e) {
		Assert.fail();
	}
	logger.info("finished test");
}
}