package wm.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wm.abstractComponents.AbstractClass;

public class GateEntryPage extends AbstractClass {
	WebDriver driver;

	public GateEntryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By docTypePanel = By.id("Document_Type-panel");
	By docNoPanel = By.id("mat-autocomplete-0");

	@FindBy(id = "Document_Type")
	WebElement receivingType;

	@FindBy(id = "DocumentNo")
	WebElement orderNo;

	@FindBy(id = "Supplier_Invoice_No")
	WebElement supplierInvoiceNo;

	@FindBy(id = "mat-input-2")
	WebElement invoiceDate;

	@FindBy(id = "Vehicle_No")
	WebElement vehicleNo;

	@FindBy(id = "Driver_Name")
	WebElement driverName;

	@FindBy(id = "Driver_Contact_No")
	WebElement driverNumber;
	@FindBy(id = "Save")
	WebElement btnSave;
	

	public void selectReceivingType(String valueToSelect) throws InterruptedException {
		// Click the mat-select to open the dropdown panel
		receivingType.click();
		Thread.sleep(100);
		receivingType.click();

		// Wait for the options panel to be visible (it usually has a specific class)
		waitElementVisibility(docTypePanel);

		// Locate all the mat-option elements within the opened dropdown
		List<WebElement> options = driver.findElements(By.cssSelector(".mat-mdc-select-panel .mat-mdc-option"));

		boolean found = false;
		for (WebElement option : options) {
			// Get the text of the option
			String optionText = option.getText().trim();
			if (optionText.equalsIgnoreCase(valueToSelect)) {
				option.click(); // Click the desired option
				found = true;
				break;
			}}
		}
		public void selectPrniter(String valueToSelect) throws InterruptedException {
			// Click the mat-select to open the dropdown panel
			receivingType.click();
			Thread.sleep(100);
			receivingType.click();

			// Wait for the options panel to be visible (it usually has a specific class)
			waitElementVisibility(docTypePanel);

			// Locate all the mat-option elements within the opened dropdown
			List<WebElement> options = driver.findElements(By.cssSelector(".mat-mdc-select-panel .mat-mdc-option"));

			boolean found = false;
			for (WebElement option : options) {
				// Get the text of the option
				String optionText = option.getText().trim();
				if (optionText.equalsIgnoreCase(valueToSelect)) {
					option.click(); // Click the desired option
					found = true;
					break;
				}
			}
		if (!found) {
			System.out.println("Warning: Receiving Type '" + valueToSelect + "' not found in dropdown.");
			// Optionally close the dropdown if not found, e.g., by pressing ESC
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.ESCAPE);
		}
		// Wait for the dropdown to close
		waitElementInvisibility(docTypePanel);
	}

	public void selectOrderNo(String valueToSelect) {
		// Type the value into the autocomplete input field
		orderNo.click();

		// Wait for the autocomplete suggestions to appear
		waitElementInvisibility(docNoPanel);
		// Locate all the mat-option elements within the autocomplete suggestions
		List<WebElement> suggestions = driver
				.findElements(By.cssSelector(".mat-mdc-autocomplete-panel .mat-mdc-option"));

		boolean found = false;
		for (WebElement suggestion : suggestions) {
			String suggestionText = suggestion.getText().trim();
			if (suggestionText.equalsIgnoreCase(valueToSelect)) {
				suggestion.click(); // Click the desired suggestion
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Warning: Order No. '" + valueToSelect + "' not found in suggestions.");
		}
		// Wait for the autocomplete panel to disappear
		waitElementInvisibility(docNoPanel);
	}

	// You can add other setter methods for the remaining fields
	public void setAllMandatoryFields(String invoiceNum, String date, String vehicleNum, String name, String number) {
		supplierInvoiceNo.sendKeys(invoiceNum);
		invoiceDate.sendKeys(date);
		vehicleNo.sendKeys(vehicleNum);
		driverName.sendKeys(name);
		driverNumber.sendKeys(number);

	}
	public void saveDetails() {
		btnSave.click();
	}

	public void navigateToGateEntry() {
		driver.get("http://180.151.246.51:1037/app/createforms/1662");
	}
}
