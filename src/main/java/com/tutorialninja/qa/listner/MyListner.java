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

public class MyListner implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extenTest;
	WebDriver driver;

	public void onStart(ITestContext context) {
		extentReport = ExtendReporter.generateExtentReport();

	}

	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extenTest = extentReport.createTest(testName);
		extenTest.log(Status.INFO, testName + "Started Executing");
	}

	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extenTest.log(Status.PASS, testName + "Successfully Excecuted ");
	}

	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		String destination = Utilities.screenShot2(driver, result.getName());
		extenTest.addScreenCaptureFromPath(destination);
		extenTest.log(Status.INFO, result.getThrowable());
		extenTest.log(Status.FAIL, testName + "Got Failed");
	}

	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		extenTest.log(Status.INFO, result.getThrowable());
		extenTest.log(Status.SKIP, testName + "Got Failed");
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathOfReport = System.getProperty("user.dir") + "\\test-output\\extentReports\\ExtentReport.html";
		File path = new File(pathOfReport);
		try {
			Desktop.getDesktop().browse(path.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
