package com.tutorialninja.qa.listner;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utilities.ExtendReporter;
import com.tutorialninja.qa.utilities.Utilities;

public class Listner implements ITestListener {

	ExtentReports extendReport;
	WebDriver driver;
	ExtentTest extendTest;

	public void onStart(ITestContext context) {
		extendReport = ExtendReporter.generateExtentReport();

	}

	public void onTestStart(ITestResult result) {
		extendTest = extendReport.createTest(result.getName());
		extendTest.log(Status.INFO, result.getName() + " Test Started");
	}

	public void onTestSuccess(ITestResult result) {
		extendTest.log(Status.PASS, result.getName() + " Successfull");
	}

	public void onTestFailure(ITestResult result) {
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		}
		String destination = Utilities.screenShots(driver, result.getName());
		extendTest.addScreenCaptureFromPath(destination);
		extendTest.log(Status.INFO,result.getThrowable());
		extendTest.log(Status.FAIL,result.getName()+" Test Fails");
	}

	public void onTestSkipped(ITestResult result) {
		extendTest.log(Status.INFO, result.getThrowable());
		extendTest.log(Status.SKIP, result.getName() + " Test Got Skipped");
	}

	public void onFinish(ITestContext context) {

		extendReport.flush();
		File path = new File(System.getProperty("user.dir")+ "\\test-output\\extentReports\\ExtentReport.html");
		try {
			Desktop.getDesktop().browse(path.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
