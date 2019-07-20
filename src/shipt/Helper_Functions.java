package shipt;

import static org.junit.Assert.assertEquals;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper_Functions {
	
// ========================= HELPER FUNCTIONS =========================	
	// ******************* DIFFERENT TAB *******************
	// Navigate to different tab
	public static void navigateToNewTab(WebDriver driver, String xpath, String expected, String actual) {

		String parent = driver.getWindowHandle();

		// Click on an xpath
		clickElementXpath(driver, xpath);

		// Wait
		implicitWait(driver, 40);

		// Navigate to different tab and Make sure by verifying text on that page text
		Set<String> allWindows = driver.getWindowHandles();
		for (String child : allWindows) {
			if (!parent.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				assertEquals(expected, actual);
				// Close tab
				driver.close();
			}
		}

		// Switch back to Shipt
		driver.switchTo().window(parent);
	}

	// ******************* CLICK ELEMENT *******************
	// By xpath
	public static void clickElementXpath(WebDriver driver, String xpath) {
		// Click on an xpath
		driver.findElement(By.xpath(xpath)).click();
	}

	// ---------------------------------------------------------------------------
	// By classname
	public static void clickElementClassName(WebDriver driver, String classname) {
		driver.findElement(By.className(classname)).click();
	}

	// ---------------------------------------------------------------------------
	// By linktext
	public static void clickElementLinkText(WebDriver driver, String text) {
		// Click on an xpath
		driver.findElement(By.linkText(text)).click();
	}

	// Implicit Wait
	public static void implicitWait(WebDriver driver, int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	// ---------------------------------------------------------------------------
	// Implicit Wait for Page Load
	public static void implicitWaitPageLoad(WebDriver driver, int time) {
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}

	// ******************* SEND KEYS *******************
	// By id
	public static void sendKeysId(WebDriver driver, String id, String Keys) {
		driver.findElement(By.id(id)).sendKeys(Keys);
	}

	// ******************* EXPLICIT WAIT *******************
	// Clickable by xpath
	public static void explicitWaitXpath(WebDriver driver, String xpath, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		clickElementXpath(driver, xpath);
	}
	
	// ---------------------------------------------------------------------------
	// Clickable by linktext
	public static void explicitWaitLinkText(WebDriver driver, String text, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(text)));
		clickElementLinkText(driver, text);
	}
}