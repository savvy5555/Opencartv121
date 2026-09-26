package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.TestBase;

	public class ExtentReportManager implements ITestListener  {
    public ExtentSparkReporter sparkReporter;//UI OF REPORT
	public ExtentReports extent;// POPULATE COMMON INFO ON REPORT
	public ExtentTest test;//CREATING TEST CASE IN TH REPORT AND UPDATE STATUS

	String repName;
	public void onStart(ITestContext testContext) {
	
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);*/
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);	
	
	sparkReporter.config().setDocumentTitle("Opencart Automation Report");//title of report
	sparkReporter.config().setReportName("opencart Functionsl Testing");//name of report
	sparkReporter.config().setTheme(Theme.STANDARD);
	
	extent=new ExtentReports();
	extent.attachReporter(sparkReporter);
	
	extent.setSystemInfo("Application", "opencart");
	extent.setSystemInfo("Module","Admin");
	extent.setSystemInfo("Sub Module","Customers");
	extent.setSystemInfo("User Name",System.getProperty("user.name"));
	extent.setSystemInfo("Environment","QA");
	
	String os= testContext.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Operatig System", os);
	
	String browser=testContext.getCurrentXmlTest().getParameter("browser");
	extent.setSystemInfo("Browser", browser);
	
	List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
	if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("Groups", includedGroups.toString());;
	}
	}
	
	public void onTestSuccess(ITestResult result) {
	    test=extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.PASS,"Test Passed" + result.getName()+"got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,"test failed"+result.getName()+"got failed");
		test.log(Status.FAIL,"test failed cause"+ result.getThrowable().getMessage());
	
		try {
			String imgPath = new TestBase().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}	
		
	
	
	}
	
	  public void onTestSkipped(ITestResult result) {
		  test=extent.createTest(result.getTestClass().getName());
		  test.log(Status.SKIP, "test skipped"+result.getName());
		  test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	
	 public void onFinish(ITestContext context) {
		    extent.flush();
		    
		    String pathOfExtentReoprt= System.getProperty("user.dir")+"\\reports\\"+repName;
		    File extentReport= new File(pathOfExtentReoprt);
		    
		    try {
		    	Desktop.getDesktop().browse(extentReport.toURI());
		    }catch(IOException e) {
		    	e.printStackTrace();
		    }
	 }
}





