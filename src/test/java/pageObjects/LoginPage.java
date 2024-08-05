package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement unameTxt;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement pwdTxt;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement noMatchAlert;
	
	public void setUserName(String email) {
		unameTxt.sendKeys(email);
	}
	
	public void setPassword(String password) {
		pwdTxt.sendKeys(password);
	}
	
	public void clickLogin() {
		loginBtn.click();
	}

	public boolean isNoMatchAlertPresent() {
		
		try {
			return noMatchAlert.isDisplayed();
			}
			catch(Exception e) {
				return false;
			}
	}
}
