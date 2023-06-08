package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.AccountPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.LoginPage;
import com.tutorialninja.qa.utilities.Utilities;
import com.tutorialsninja.qa.base.Base;

public class LoginTest extends Base {
	
	public WebDriver driver;
		
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
		driver = startupBrowserandApp(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.MyAccountClick();
		homepage.LoginClick();
				
	}
	
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority = 1, dataProvider = "data")
	public void VerifyLoginValid(String email, String password)  {
		
		AccountPage accountpage = new AccountPage(driver);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.EmailField(email);
		loginpage.PasswordField(password);
		loginpage.LoginButton();
				
		Assert.assertTrue(accountpage.VerifyAccountText());
		
	}
	@Test(priority =2)
	public void InvalidLogin() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.EmailField(Utilities.GenerateRandom());
		loginpage.PasswordField(dataprop.getProperty("InvalidPassword"));
		loginpage.LoginButton();
				
		Assert.assertTrue(loginpage.NoMatchWarning(),"Expected Warning");
	}
	
	@DataProvider (name="data")
	public Object[][] Data() {
		
		Object[][] data = Utilities.dataSupply("Login");
		return data;
	}
	
	
}
