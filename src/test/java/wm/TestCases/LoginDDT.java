package wm.TestCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import wm.PageObjects.HomePage;
import wm.TestComponents.BaseTest;
import wm.TestComponents.DataProviders;
import wm.TestComponents.Retry;

public class LoginDDT extends BaseTest {

	@Test(groups = {
			"negative" }, retryAnalyzer = Retry.class, dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void logintest_002_valid_email_invalid_pass(String order, String type, String email, String pass, String msg)
			throws IOException {
		System.out.println("Executing test case: " + order + " | Type: " + type);
		if (type.equalsIgnoreCase("Positive")) {

			loginPage.login(email, pass);
			HomePage homePage = new HomePage(driver);
			String message = homePage.getLoginMessage();
			Assert.assertEquals(message, msg);
			homePage.logOut();
			System.out.println(driver.getCurrentUrl());
		} else if (type.equalsIgnoreCase("Negative")) {
			loginPage.login(email, pass);
			String message = loginPage.getMessage();
			// System.out.println(message);
			Assert.assertEquals(message, msg);
		} else if (!type.equalsIgnoreCase("Positive") && !type.equalsIgnoreCase("Negative")) {
	        System.out.println("Un-Identified test Case type!");
	        throw new SkipException("Skipping test due to unknown test type: " + type);
	    }
	}
}
