package wm.TestCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import wm.PageObjects.GateEntryPage;
import wm.PageObjects.HomePage;
import wm.TestComponents.BaseTest;
import wm.TestComponents.DataProviders;
import wm.TestComponents.Retry;

public class LoginDDT extends BaseTest {

	@Test(groups = {
			"negative" }, retryAnalyzer = Retry.class, dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void logintest(String order, String type, String username, String pass, String msg) throws IOException {
		System.out.println("Executing test case: " + order + " | Type: " + type);
		if (type.equalsIgnoreCase("Positive")) {

			loginPage.login(username, pass);
			HomePage homePage = new HomePage(driver);
			String message = homePage.getLoginMessage();
			Assert.assertEquals(message, msg);
			homePage.logOut();
			System.out.println(driver.getCurrentUrl());
		} else if (type.equalsIgnoreCase("Negative")) {
			loginPage.login(username, pass);
			String message = loginPage.getMessage();
			// System.out.println(message);
			Assert.assertEquals(message, msg);
		}
		if (!type.equalsIgnoreCase("Positive") && !type.equalsIgnoreCase("Negative")) {
			System.out.println("Un-Identified test Case type!");

			// Custom skip message without stack trace
			SkipException skip = new SkipException("Skipping test due to unknown test type: " + type);
			skip.setStackTrace(new StackTraceElement[0]); // suppress stack trace
			throw skip;
		}

	}

	@Test(retryAnalyzer = Retry.class, dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void gateEntryTest(String TestCases, String type, String username, String password, String docType,
			String docNo, String invNum, String date, String vehicleNum, String name, String number)
			throws InterruptedException {
		// Checking the type of Test case
		if (type.equalsIgnoreCase("Positive")) {

			loginPage.login(username, password);
			HomePage homePage = new HomePage(driver);
			String message = homePage.getLoginMessage();
			GateEntryPage gateIn = new GateEntryPage(driver);
			gateIn.navigateToGateEntry();
			gateIn.selectReceivingType(docType);
			gateIn.selectOrderNo(docNo);
			gateIn.setAllMandatoryFields(invNum, date, vehicleNum, name, number);
			gateIn.saveDetails();
			homePage.logOut();

		}
	}
}