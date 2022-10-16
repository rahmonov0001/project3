package automation;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	private By login = By.xpath("//span[text()='Войти']");
	private By searchButton = By.cssSelector("button[type='submit']");
	private By keyboard = By.cssSelector("[href*='open_keyboard']");
	private By searchBox = By.cssSelector("[placeholder='найдётся всё']");
	
	private By lanSwitcher = By.cssSelector(".keyboard__switcher");
	private By lanPopup = By.cssSelector("[class*='popup2_direction_top-left popup2_visible_yes']");
	private By language = By.cssSelector("[class*='keyboard__lang keyboard']");
	
	private By keypanel = By.cssSelector(".keyboard__key-m");
	
	private By searchLink = By.cssSelector("a.Link");
	
	public LoginPage clickLoginButton() {
		new WebDriverWait(driver, Duration.ofSeconds(30))
		.until(ExpectedConditions.elementToBeClickable(login)).click();
		return new LoginPage(driver);
	}
	public String getHomePageTitle() {
		new WebDriverWait(driver, Duration.ofSeconds(30))
		.until(ExpectedConditions.elementToBeClickable(searchButton));
		return driver.getTitle();
	}
	public HomePage clickSearchKeyboard() {
		new WebDriverWait(driver, Duration.ofSeconds(30))
		.until(ExpectedConditions.elementToBeClickable(keyboard)).click();
		Iterator<String> iterator = driver.getWindowHandles().iterator();				
		String parent = iterator.next();
		String child = iterator.next();		
		driver.switchTo().window(child);	
		return this;
	}
	public HomePage setLanguageForSearch(String lang) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(searchBox)).click();
		wait.until(ExpectedConditions.elementToBeClickable(lanSwitcher)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(lanPopup));		
		List<WebElement> listLanguage = driver.findElements(language);		
		for(int i = 0; i < listLanguage.size(); i++) {
			if(listLanguage.get(i).getText().equalsIgnoreCase("English")) {
				listLanguage.get(i).click();
				break;
			}				
		}		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(lanPopup));	
		return this;
	}
	public HomePage setWordForSearch(String word) {
		List<WebElement> keys = driver.findElements(keypanel);
		String t = "";
		for (int i = 0; i < word.length(); i++) {
			t += word.charAt(i);
			for (int j = 0; j < keys.size(); j++) {
				if (keys.get(j).getText().equals(t)) {
					keys.get(j).click();
					t = "";
					break;
				}
			}
		}
		return this;
	}
	public HomePage clickSearchButton() {
		new WebDriverWait(driver, Duration.ofSeconds(30))
		.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
		return this;
	}
	public boolean checkSearchIsSuccess(String word) {
		new WebDriverWait(driver, Duration.ofSeconds(30))
		.until(ExpectedConditions.elementToBeClickable(searchLink));
		return driver.findElements(searchLink).get(1).getText().contains(word);
	}
}