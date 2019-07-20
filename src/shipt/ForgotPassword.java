package shipt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

//Imports from Methods_ForgotPassword
import static shipt.Methods_ForgotPassword.logIn;
import static shipt.Methods_ForgotPassword.gmail;
import static shipt.Methods_ForgotPassword.switchToShipt;

public class ForgotPassword {

public static void main(String args[]) throws Exception {

	// Execute in Incognito Window
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("incognito");
	capabilities.setCapability(ChromeOptions.CAPABILITY, options);

	@SuppressWarnings("deprecation")
	WebDriver driver = new ChromeDriver(capabilities);
		
	logIn(driver);

	gmail(driver);

	switchToShipt(driver);

	// Quit browser
	driver.quit();
	}
}
