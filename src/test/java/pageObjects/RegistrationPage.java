package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement confirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement agreeCheckbox;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueBtn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmationMsg;
	
	public void setFirstName(String fName) {
		firstName.sendKeys(fName);
	}
	
	public void setLastName(String lName) {
		lastName.sendKeys(lName);
	}
	
	public void setEmail(String e_mail) {
		email.sendKeys(e_mail);
	}
	
	public void setTelephone(String telePhone) {
		telephone.sendKeys(telePhone);
	}
	
	public void setPassword(String passWord) {
		password.sendKeys(passWord);
	}
	
	public void setConfirmPassword(String passWord) {
		confirmPassword.sendKeys(passWord);
	}
	
	public void setPrivacyPolicy() {
		agreeCheckbox.click();
	}
	
	public void setContinue() {
		continueBtn.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return confirmationMsg.getText();
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}

}
