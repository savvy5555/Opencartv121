package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import TestBase.TestBase;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends TestBase {
		

	@Test(groups= {"Regression","Master"})	
	public void verifyAccountInformation() throws InterruptedException {
		try {
		logger.info("Starting TC001_AccountRegistrationTest");
			
		HomePage hm=new HomePage(driver);
		AccountRegistrationPage ar= new AccountRegistrationPage(driver);
		
		logger.info("clicking on home page");
		
		hm.clickMyAccount();
		
		logger.info("clicking on register");
		hm.clickRegister();
		
		Thread.sleep(5000);
		
		logger.info("providing information");
		ar.fname(randomString());
		ar.lname(randomString());
		ar.entemail(randomString()+"@"+"gmail.com");
		ar.tphone(randomNmbr());
		
		String p=randomString();
		ar.psid(p);
		ar.cpsid(p);
		ar.rd();
		ar.contibtn();
		String msg=ar.getConfirmation();
		Assert.assertEquals(msg, "Your Account Has Been Created!");
		Thread.sleep(3000);
	}
		catch(Exception e) {
			logger.error("Test failed..");
			logger.debug("debug logs");
			Assert.fail();
		}
		
		logger.info(" finished TC001_AccountRegistrationTest");
	}
	
}
