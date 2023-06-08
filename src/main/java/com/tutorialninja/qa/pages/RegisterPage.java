package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstName;

	@FindBy(id = "input-lastname")
	private WebElement lastName;
	
	@FindBy(id = "input-email")
	private WebElement Email;
	
	@FindBy(id = "input-telephone")
	private WebElement Phone;
	
	@FindBy(id = "input-password")
	private WebElement Password;
	
	@FindBy(id= "input-confirm")
	private WebElement ConfirmPassword;
	
	@FindBy(name = "agree")
	private WebElement radioButton;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//h1[contains(text(),'Your Account Has Been Created!')]")
	private WebElement AccountCreateMsg;
	
	@FindBy(xpath = "//label[normalize-space()='Yes']")
	private WebElement newsLetter;
	
	@FindBy(xpath = "//div[contains(text(),'Warning: E-Mail Address is already registered!')]")
	private WebElement existEmailMsg;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void firstName(String FirstName) {
		firstName.sendKeys(FirstName);
	}
	public void lastName(String LastName) {
		lastName.sendKeys(LastName);
	}
	public void Email(String email) {
		Email.sendKeys(email);
	}
	public void phone(String phone) {
		Phone.sendKeys(phone);
	}
	public void password(String password) {
		Password.sendKeys(password);
	}
	public void confirmPass(String password) {
		ConfirmPassword.sendKeys(password);
	}
	public void radioBtn() {
		radioButton.click();
	}
	public void contBtn() {
		continueButton.click();
	}
	public String accountCreate() {
		String confirm = AccountCreateMsg.getText();
		return confirm;
	}
	public void newsletter() {
		newsLetter.click();
	}
	public String ExistEmailMsg() {
		String msg = existEmailMsg.getText();
		return msg;
	}
}

