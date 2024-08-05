package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

//Data is valid - login success - test pass - logout
//data is valid - login failed - test fail
//Data is Invalid - login success - test fail- logout
//data is Invalid - login failed - test pass

public class TC003_LoginDataDrivenTest extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="datadriven")
	public void verifyLogin(String email, String password, String exp) {
		
		try {
				HomePage homePage = new HomePage(driver);	
			    homePage.clickMyAccount();
			    homePage.clickLogin();
			    
			    LoginPage loginPage = new LoginPage(driver);
			    loginPage.setUserName(email);
			    loginPage.setPassword(password);
			    loginPage.clickLogin();
			    
				MyAccountPage myAccountPage = new MyAccountPage(driver);
			    boolean myAccount = myAccountPage.isMyAccountPageExists();
			    boolean alert = loginPage.isNoMatchAlertPresent();
			    
			    if(exp.equalsIgnoreCase("Valid")) {
			    	
			    	if(myAccount==true) {
			    		
			    		myAccountPage.clickLogout();
			    		Assert.assertTrue(true);
			    		
			    		}

			    	else {
			    		Assert.assertTrue(false);
			    		}
			    }
			    
			    if(exp.equalsIgnoreCase("Invalid")) {
			    	
			    	if(myAccount==true) {
			    		
			    		myAccountPage.clickLogout();
			    		Assert.assertTrue(false);
			    	}
			    	else {
			    		
			    		Assert.assertTrue(true);
			    	}
			    }
		}
			    catch(Exception e) {
			    	e.printStackTrace();
			    }
	}

}
