package wm.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wm.abstractComponents.AbstractClass;

public class LoginPage extends AbstractClass {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "mat-input-0")
	WebElement userEmail;

	@FindBy(id = "mat-input-1")
	WebElement userPassword;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginButton;
	
	@FindBy(id = "swal2-html-container")
	WebElement errorMessage;
	
	By popUp = By.xpath("//div[@role='dialog']");

	public HomePage login(String email,String pass) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(pass);
		loginButton.click();
		System.out.println("Login Done");
		HomePage homePage = new HomePage(driver);
		return homePage;
	}
	public String getMessage() {
		waitElementVisibility(popUp);
		String fmessage = errorMessage.getText();
		System.out.println("Message Printed");
		// waitElementInvisibility(CopyRights);
		return fmessage;
	}

	public void goToLoginPage() {
		driver.get("http://180.151.246.51:1037/account/login");

	}

}
