package shipt;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// Imports from Helper Functions
import static shipt.Helper_Functions.clickElementXpath;
import static shipt.Helper_Functions.clickElementClassName;
import static shipt.Helper_Functions.clickElementLinkText;
import static shipt.Helper_Functions.sendKeysId;
import static shipt.Helper_Functions.explicitWaitLinkText;
import static shipt.Helper_Functions.explicitWaitXpath;
import static shipt.Helper_Functions.implicitWait;


public class Methods_ForgotPassword {
		
// ============================== METHODS ==============================	
	// ********************* FORGOT PASSWORD *******************
	public static void logIn(WebDriver driver) throws Exception {
		
		// Navigate to Shipt homepage
		driver.get("https://www.shipt.com/");
		
		// Page Refresh
		driver.navigate().refresh();

		// Click on Log In button
		clickElementClassName(driver, "button-secondary");
		
		// Click on Forgot Password link
		clickElementLinkText(driver, "Forgot Password?");

		// Enter User Name
		sendKeysId(driver, "email", "mytestacc6543@gmail.com");

		// Click on Recover Password button
		clickElementClassName(driver, "btn");

		// Make sure it is navigated to correct page by verifying text
		assertEquals("Check your email for password reset instructions", 
				"Check your email for password reset instructions");
	}

	// ********************* GMAIL *******************
	public static void gmail(WebDriver driver) throws IOException, InterruptedException {
		
		// Navigate to gmail
		driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");

		GmailPageObject gp = PageFactory.initElements(driver, GmailPageObject.class);

		// Enter Email
		gp.enterEmail("mytestacc6543@gmail.com");

		// Enter Password
		gp.enterPassword("dummy@1234");

		// Click on specific email
		gp.clickEmail("Reset Your Shipt Password");

		// Wait and Click button from email
		explicitWaitLinkText(driver, "CREATE OR RESET PASSWORD", 10);

		// Wait and Delete Email
		explicitWaitXpath(driver, "(//div[@class='asa'])[14]", 10);
	}

	// ********************* SWITCH TO SHIPT *******************
	public static void switchToShipt(WebDriver driver) {
		
		// Switch control to new tab(shipt)
		for (String newTab : driver.getWindowHandles()) {
			driver.switchTo().window(newTab);
		}
		
		// Make sure it is navigated to correct page by verifying text
		assertEquals("Choose a New Password","Choose a New Password");
		
		// Enter Password
		sendKeysId(driver, "customer_password", "dummy@1234");

		// Enter Password confirmation
		sendKeysId(driver, "customer_password_confirmation", "dummy@1234");

		// Click on Update Password button
		clickElementXpath(driver, "//input[@name='commit']");
		
		implicitWait(driver, 100);

		// Make sure sure navigated to right page by verifying URL
		String URL = driver.getCurrentUrl();
		assertEquals(URL, "https://app.shipt.com/");
		
		assertEquals("Password was successfully updated", "Password was successfully updated");
	}	
}

