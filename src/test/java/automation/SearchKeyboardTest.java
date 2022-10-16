package automation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Base;

public class SearchKeyboardTest extends Base {

	public WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	
	@BeforeTest
	public void initialize() {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		driver.get("https://yandex.ru");
	}

	@Test
	public void keyboardValidation() {
		boolean isSuccess = homePage.clickSearchKeyboard()
		.setLanguageForSearch("English")
		.setWordForSearch("planeta")
		.clickSearchButton()
		.checkSearchIsSuccess("planeta");
		Assert.assertTrue(isSuccess);
	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		driver.quit();
	}
}
