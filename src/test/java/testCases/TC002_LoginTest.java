package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"sanity", "master"})
	public void verifyLogin() {
		
		HomePage homePage = new HomePage(driver);	
	    homePage.clickMyAccount();
	    homePage.clickLogin();
	    
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.setUserName(prop.getProperty("email"));
	    loginPage.setPassword(prop.getProperty("password"));
	    loginPage.clickLogin();
	    
		MyAccountPage myAccountPage = new MyAccountPage(driver);
	    boolean myAccount = myAccountPage.isMyAccountPageExists();
	    
	    Assert.assertTrue(myAccount);
	}
}
