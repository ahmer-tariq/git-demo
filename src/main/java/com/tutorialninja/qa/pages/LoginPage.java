package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	@FindBy(id = "input-email")
	private WebElement EmailField;
	
	@FindBy(id = "input-password")
	private WebElement PasswordField;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]")
	private WebElement NoMatchWarning;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void EmailField(String email) {
		 EmailField.sendKeys(email);
		
	}
	public void PasswordField(String password) {
		PasswordField.sendKeys(password);
	}
	public void LoginButton() {
		loginButton.click();
	}
	public Boolean NoMatchWarning() {
		Boolean Warning = NoMatchWarning.isDisplayed();
		return Warning;
	}
}
