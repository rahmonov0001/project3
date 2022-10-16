package automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By username = By.cssSelector("input[name='login']");
	private By submit = By.cssSelector("button[type='submit']");
	private By password = By.id("passp-field-passwd");
	
	public LoginPage setUserName(String userName) {
		new WebDriverWait(driver, Duration.ofSeconds(30))
		.until(ExpectedConditions.elementToBeClickable(username)).sendKeys(userName);
		return this;
	}
	public LoginPage clickSubmitButton() {
		new WebDriverWait(driver, Duration.ofSeconds(30))
		.until(ExpectedConditions.elementToBeClickable(submit)).click();
		return this;
	}
	public LoginPage setPassword(String passWord) {
		new WebDriverWait(driver, Duration.ofSeconds(30))
		.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(passWord);
		return this;
	}
}