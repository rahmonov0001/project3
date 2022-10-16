package automation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.Base;
import resources.ExcelUtils;

public class LoginAppTest extends Base {

	public WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	
	@BeforeTest
	public void initialize() {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		driver.get("https://yandex.ru");
	}

	@Test(dataProvider = "getData")
	public void loginAppValidation(String username, String password) {
		loginPage = homePage.clickLoginButton();
		loginPage.setUserName(username).clickSubmitButton()
		.setPassword(password).clickSubmitButton();		
		Assert.assertEquals(homePage.getHomePageTitle(), "Дзен");
	}

	@DataProvider
	public Object[][] getData() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("TestData.xlsx", "Sheet1");
		return testObjArray;
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}