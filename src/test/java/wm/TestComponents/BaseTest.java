package wm.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import wm.PageObjects.LoginPage;

public class BaseTest {
	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\wm\\Resources\\GlobalData.properties");

		prop.load(fis);

		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
		}
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.chromedriver().setup();

			driver = new FirefoxDriver();
		}
		if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.chromedriver().setup();

			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	public List<HashMap<String, String>> getJSONDataToMap(String filePath) throws IOException {
		// Define the path to the JSON file using File.separator for cross-platform
		// compatibility
		;

		// Create a File object
		File jsonFile = new File(filePath);

		// Check if the file exists
		if (!jsonFile.exists()) {
			throw new IOException("File not found at path: " + filePath);
		}

		// Read JSON file into a string using File object
		String jsonContent = FileUtils.readFileToString(jsonFile, "UTF-8");

		// Convert JSON string to List of HashMaps
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File screenshot = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot,
				new File(System.getProperty("user.dir") + "\\screenshots/" + testCaseName + ".png"));
		return System.getProperty("user.dir") + "\\screenshots/" + testCaseName + ".png";
	}

	@DataProvider
	public Object[][] getloginDataFromJSON() throws IOException {
		List<HashMap<String, String>> data = getJSONDataToMap(System.getProperty("user.dir") + File.separator + "src"
				+ File.separator + "test" + File.separator + "java" + File.separator + "WM" + File.separator
				+ "TestData" + File.separator + "LoginDataValidation.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goToLoginPage();
		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
}
