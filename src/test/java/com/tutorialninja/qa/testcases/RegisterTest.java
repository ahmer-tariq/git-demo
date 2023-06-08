package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.RegisterPage;
import com.tutorialninja.qa.utilities.Utilities;
import com.tutorialsninja.qa.base.Base;

public class RegisterTest extends Base {

	public WebDriver driver;

	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = startupBrowserandApp(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.MyAccountClick();
		homepage.RegisterClick();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void RegisterWithMandatoryFeilds() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.firstName(dataprop.getProperty("FirstName"));
		registerpage.lastName(dataprop.getProperty("LastName"));
		registerpage.Email(Utilities.GenerateRandom());
		registerpage.phone(dataprop.getProperty("Phone"));
		registerpage.password(dataprop.getProperty("Password"));
		registerpage.confirmPass(dataprop.getProperty("Password"));
		registerpage.radioBtn();
		registerpage.contBtn();

		String ActaulText = registerpage.accountCreate();
		Assert.assertEquals(ActaulText, dataprop.getProperty("AccountCreateMsg"),
				"Account Success page is not displayedd");

	}

	@Test(priority = 2)
	public void RegusterWithRadiobutton() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.firstName(dataprop.getProperty("FirstName"));
		registerpage.lastName(dataprop.getProperty("LastName"));
		registerpage.Email(Utilities.GenerateRandom());
		registerpage.phone(dataprop.getProperty("Phone"));
		registerpage.password(dataprop.getProperty("Password"));
		registerpage.confirmPass(dataprop.getProperty("Password"));
		registerpage.newsletter();
		registerpage.radioBtn();
		registerpage.contBtn();

		String ActaulText = registerpage.accountCreate();

		Assert.assertEquals(ActaulText, dataprop.getProperty("AccountCreateMsg"),
				"Account Success page is not displayedd");

	}

	@Test(priority = 3)
	public void DuplicateEmail() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.firstName(dataprop.getProperty("FirstName"));
		registerpage.lastName(dataprop.getProperty("LastName"));
		registerpage.Email(dataprop.getProperty("ExistEmail"));
		registerpage.phone(dataprop.getProperty("Phone"));
		registerpage.password(dataprop.getProperty("Password"));
		registerpage.confirmPass(dataprop.getProperty("Password"));
		registerpage.newsletter();
		registerpage.radioBtn();
		registerpage.contBtn();

		String ActualText = registerpage.ExistEmailMsg();
		Assert.assertEquals(ActualText, dataprop.getProperty("WarningMsg"), "Duplicate Email accepts. Test Fails");

	}

	@Test(priority = 4)
	public void RegisterWithFieldEmpty() {

		RegisterPage registerpage = new RegisterPage(driver);
		registerpage.contBtn();

		String ActualText = driver.findElement(By.xpath("//div[contains(@class,'alert alert')]")).getText();

		Assert.assertEquals(ActualText, dataprop.getProperty("PolicyMsg"), "Privacy Policy not displayed Test Fails");

		String ActualUser = driver
				.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]"))
				.getText();
		Assert.assertTrue(ActualUser.contains(dataprop.getProperty("UserFieldMsg")),
				"First Name Warning not displayed");

		String ActualPass = driver
				.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]"))
				.getText();
		Assert.assertTrue(ActualPass.contains(dataprop.getProperty("LastNameMsg")), "Last Name Warning not displayed");

		String Actualemail = driver
				.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]"))
				.getText();
		Assert.assertTrue(Actualemail.contains(dataprop.getProperty("EmailMsg")), "Email Warning not displayed");

	}
}
