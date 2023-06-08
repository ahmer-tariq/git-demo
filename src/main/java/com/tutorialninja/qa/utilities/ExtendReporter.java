package com.tutorialninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReporter {
	
	public static ExtentReports generateExtentReport() {
		
	ExtentReports extent = new ExtentReports();
	File extentFile = new File(System.getProperty("user.dir") + "\\test-output\\extentReports\\ExtentReport.html");
	ExtentSparkReporter spark = new ExtentSparkReporter(extentFile);
	spark.config().setTheme(Theme.DARK);
	spark.config().setReportName("TutorialsNinja Test Automation Results Reports");
	spark.config().setDocumentTitle("TN Automation Report");
	spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extent.attachReporter(spark);
	Properties configProp = new Properties();
	File file = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
	try {
	FileInputStream fis = new FileInputStream(file);
	configProp.load(fis);
	} catch (Throwable e) {
		e.printStackTrace();
	}
	extent.setSystemInfo("Application URL", configProp.getProperty("url"));
	extent.setSystemInfo("Browser Name", configProp.getProperty("browser"));
	extent.setSystemInfo("Email", configProp.getProperty("validemail"));
	extent.setSystemInfo("Password", configProp.getProperty("password"));
	extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	extent.setSystemInfo("UserName", System.getProperty("user.name"));
	extent.setSystemInfo("Java Version", System.getProperty("java.version"));
	
	return extent;
	}
	


}
