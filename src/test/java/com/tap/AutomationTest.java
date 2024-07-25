package com.tap;

import com.tap.pages.HomePage;
import com.tap.pages.ResponsePage;
import com.tap.pages.ThreeDSPage;
import com.tap.utils.ConfigReader;
import com.tap.utils.Log;

import absttractComponent.AbstractComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AutomationTest {

	private WebDriver driver;
	private HomePage homePage;
	private ThreeDSPage threeDSPage;
	private ResponsePage responsePage;
	private ConfigReader config;

	@BeforeClass
	public void setUp() {

		driver = new SafariDriver();

		homePage = new HomePage(driver);
		threeDSPage = new ThreeDSPage(driver);
		responsePage = new ResponsePage(driver);
		config = new ConfigReader();
		Log.info("Test setup completed");
	}

	@DataProvider(name = "cardData")
	public Object[][] cardData() {
		return new Object[][] { { "5123450000000008", "01/39", "100" }, { "4508750015741019", "01/39", "100" } };

	}

	@Test(dataProvider = "cardData")
	public void testCardAutomation(String cardNumber, String expiryDate, String cvv) throws InterruptedException {

		driver.get("https://demo.dev.tap.company/v2/sdk/card");
		driver.manage().window().maximize();

		AbstractComponent abstComp = new AbstractComponent(driver);

		abstComp.waitForWebElementToAppear(abstComp.currencyDropdown);

		homePage.selectCurrency("BHD");
		abstComp.waitForWebElementToAppear(abstComp.scopeDropdown);
		homePage.selectScope("Authenticated Token");

		Thread.sleep(10000);
		homePage.swithchToIframe();
		homePage.enterCardDetails(cardNumber, expiryDate, cvv);
		

		driver.switchTo().defaultContent();
		homePage.scrollUntilElementFound();
		homePage.clickGenerateToken();

		threeDSPage.scrollUpUntilEmenentFound();

		Thread.sleep(10000);
		System.out.println("111111");
		threeDSPage.swithchIframe();
		threeDSPage.clickSubmit();
		Thread.sleep(10000);

		driver.switchTo().defaultContent();

		responsePage.clickResponseSection();
		abstComp.waitForWebElementToAppear(abstComp.responseData);

		String responseData = responsePage.getResponseData();
		System.out.println("Response Data: " + responseData);

		Log.info("Response Data: " + responseData);
		Thread.sleep(10000);
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		Log.info("Test teardown completed");
	}
}
