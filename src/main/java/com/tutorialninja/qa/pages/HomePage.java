package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//span[contains(text(),'My Account')]")
	private WebElement MyAccount;

	@FindBy(linkText = "Login")
	private WebElement Login;
	
	@FindBy(linkText = "Register")
	private WebElement Register;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void MyAccountClick() {
		MyAccount.click();
	}
	public void LoginClick() {
		Login.click();
	}
	public void RegisterClick() {
		Register.click();
	}
}
