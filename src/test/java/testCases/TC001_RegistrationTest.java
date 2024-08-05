package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_RegistrationTest extends BaseClass {
	
	@Test(groups={"regression", "master"})
	public void verifyAccountRegistration() {
		
		HomePage homePage = new HomePage(driver);
		RegistrationPage registrationPage = new RegistrationPage(driver);
	    homePage.clickMyAccount();
	    homePage.clickRegister();
	    
	    registrationPage.setFirstName(randomString().toUpperCase());
	    registrationPage.setLastName(randomString().toUpperCase());
	    registrationPage.setEmail(randomString()+"@gmail.com");
	    registrationPage.setTelephone(randomNumber());
	    
	    String password = randomAlphaNumber();
	    System.out.println(password);
	    registrationPage.setPassword(password);
	    registrationPage.setConfirmPassword(password);
	    
	    registrationPage.setPrivacyPolicy();
		registrationPage.setContinue();
		
		String confirmMsg = registrationPage.getConfirmationMsg();
		Assert.assertEquals(confirmMsg, "Your Account Has Been Created!");
	}
	
}
