package wm.TestComponents;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import wm.Resources.ExtentReporterNG;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

public class Listeners extends BaseTest implements ITestListener {
//WebDriver driver;
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.extentReports();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	// This method is invoked before each test starts
	@Override
	public void onTestStart(ITestResult result) {
		// System.out.println("Test Started: " + result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	// This method is invoked each time a test succeeds
	@Override
	public void onTestSuccess(ITestResult result) {
		// System.out.println("Test Passed: " + result.getName());
		test.log(Status.PASS, "Test Passed");

	}

	// This method is invoked each time a test fails
	@Override
	public void onTestFailure(ITestResult result) {
		// System.out.println("Test Failed: " + result.getName());
		extentTest.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Take Screenshot
		try {
			String filePath = takeScreenshot(result.getMethod().getMethodName(), driver);
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// This method is invoked each time a test is skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
	}

	// This method is invoked when a test fails but within the success percentage
	// defined
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test failed but within success percentage: " + result.getName());
	}

	// This method is invoked at the start of the test context (e.g., before a test
	// suite or test group runs)
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Context Started: " + context.getName());
	}

	// This method is invoked at the end of the test context (e.g., after a test
	// suite or test group completes)
	@Override
	public void onFinish(ITestContext context) {
		// System.out.println("Test Context Finished: " + context.getName());

		extent.flush();
	}
}
