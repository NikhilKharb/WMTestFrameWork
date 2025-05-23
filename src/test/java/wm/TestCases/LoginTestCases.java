package wm.TestCases;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

import wm.PageObjects.HomePage;
import wm.TestComponents.BaseTest;
import wm.TestComponents.Retry;

public class LoginTestCases extends BaseTest {

	@Test(groups = { "negative" }, retryAnalyzer = Retry.class, dataProvider = "getloginDataFromJSON")
	public void logintest_002_valid_email_invalid_pass(HashMap<String, String> input) throws IOException {
		HomePage homePage = loginPage.login(input.get("email"), input.get("password"));
		//String message = homePage.getLoginMessage();
		//Assert.assertEquals(message, input.get("message"));
	}

}

//@Test
//public void logintest_001_valid_creds() throws IOException {
//
//	HomePage homePage = landingPage.login("nikhil.kharb@ymail.com", "Test@123");
//	String message = homePage.getLoginMessage();
//	Assert.assertTrue(message.equalsIgnoreCase("Login Successfully"));
//	homePage.logOut();
//}

//@Test(groups= {"negative"},dataProvider = "getDataMap")
//public void logintest_003_invalid_email_valid_pass() throws IOException {
//
//	HomePage homePage = landingPage.login("nikhil.kharb@yuiymail.com", "Test@123");
//	String message = homePage.getLoginMessage();
//	Assert.assertTrue(message.equalsIgnoreCase("Incorrect email or password."));
//
//}
