package wm.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wm.abstractComponents.AbstractClass;

public class HomePage extends AbstractClass {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By copyRights = By.xpath("//div[@class='copyright']");
	By userDetails = By.xpath("//div[@class='toggletopbar top-menu']");
	
	// By loader = By.cssSelector(".ng-animating");

	@FindBy(css = "#toast-container")
	WebElement message;

	@FindBy(xpath = "//div[@class='copyright']")
	WebElement coppyRightsText;

	@FindBy(xpath = "(//i[@class='material-icons material-icon-action md-24'])[1]")
	WebElement userIcon;

	@FindBy(xpath = "//div[@class='logout_footer_sidebar']")
	WebElement logOutBtn;

	public void logOut() {
		System.out.println(driver.getCurrentUrl());
		userIcon.click();
		waitElementVisibility(userDetails);
		logOutBtn.click();
		System.out.println("Logout Done");
	}

	public String getLoginMessage() {
		waitElementVisibility(copyRights);
		String fmessage = coppyRightsText.getText();
		// waitElementInvisibility(CopyRights);
		return fmessage;
	}

}
