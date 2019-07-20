package shipt;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Imports from Helper_Functions
import static shipt.Helper_Functions.implicitWait;

public class GmailPageObject {
	
	public WebDriver driver;

	@FindBy(how = How.XPATH, xpath = "//input[@id='identifierId']")
	WebElement emailField;

	@FindBy(how = How.XPATH, xpath = "//*[@id='password']/div[1]/div/div[1]/input")
	WebElement passwordField;

	@FindBy(how = How.XPATH, xpath = "//span[@class='bog']")
	List<WebElement> emailThreads;

	@FindBy(how = How.XPATH, xpath = "//span[@class='gb_za gbii']")
	WebElement profileLogo;

	@FindBy(how = How.XPATH, xpath = "(//div[@class='asf T-I-J3 J-J5-Ji'])[3]")
	WebElement refresh;

	public GmailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	// ********************* EMAIL *******************
	public void enterEmail(String emailID) {
		waitForVisible(driver, emailField);
		Actions actions = new Actions(driver);
		actions.moveToElement(emailField);
		actions.click();
		actions.sendKeys(emailID + Keys.ENTER);
		actions.build().perform();
	}

	// ********************* PASSWORD *******************
	public void enterPassword(String password) {
		waitForVisible(driver, passwordField);
		Actions actions = new Actions(driver);
		actions.moveToElement(passwordField);
		actions.click();
		actions.sendKeys(password + Keys.ENTER);
		actions.build().perform();
	}

	// ********************* CLICK EMAIL *******************
	public void clickEmail(String emailSubject) throws InterruptedException {
		waitForVisible(driver, profileLogo);
		
		// Wait till the page loads
		//Thread.sleep(15000);
		
		// Page Refresh
		driver.navigate().refresh();
		
		for (int i = 0; i < emailThreads.size(); i++) {
			if (emailThreads.get(i).getText().contains(emailSubject)) {
				emailThreads.get(i).click();
				break;
			} 
		}
	}

	// ********************* WAIT *******************
	public void waitForVisible(WebDriver driver, WebElement element) {
		try {
			// Wait
			implicitWait(driver, 30);

			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
