package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	WebDriver driver;
	
	//constructor
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	//locator
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement myAccount;
		
	@FindBy(linkText = "Register")
	WebElement register;
		
	@FindBy(linkText = "Login")
	WebElement login;
		
	//page actions
	public void clickMyAccount() {
		myAccount.click();
	}
	
	public void clickRegister() {
		register.click();
	}
	
	public void clickLogin() {
		login.click();
	}
}
