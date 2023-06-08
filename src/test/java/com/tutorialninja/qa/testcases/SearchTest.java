package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

public class SearchTest extends Base {
	
	public WebDriver driver;
	
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
		driver = startupBrowserandApp(prop.getProperty("browser"));
	}

	
	@Test(priority = 1)
	public void searchValid() {
		
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("HP");
		driver.findElement(By.xpath("//button[contains(@class,'btn-lg')]")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
	
	@Test (priority = 2)
	public void searchInvalid() {
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("honda");
		driver.findElement(By.xpath("//button[contains(@class,'btn-lg')]")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]")).isDisplayed());
	}
	
	@Test(priority = 3)
	public void searchempty() {
		
		driver.findElement(By.xpath("//button[contains(@class,'btn-lg')]")).click();
		String ActualText = driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]")).getText();
		Assert.assertEquals(ActualText, "There is no product that matches the search criteria.", "No Text found");
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
