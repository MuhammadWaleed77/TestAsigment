package com.tap.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tap.utils.Log;

public class ThreeDSPage extends BasePage {

	public ThreeDSPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div//div[@id='ContainerContent']//input[@id='acssubmit']")

	private WebElement submitButton;

	@FindBy(id = "Config")
	private WebElement ConfigBtn;

	@FindBy(id = "challengeFrame")
	private WebElement iframeChallenge;

	@FindBy(id = "tap-card-iframe-authentication")
	private WebElement iframeSecond;

	@FindBy(id = "tap-card-iframe")
	private WebElement iFrame;

	public void clickSubmit() {
		Log.info("Clicking Submit button on 3DS page");
		submitButton.click();
	}

	public void swithchIframe() {

		try {

			Log.info("Switching to Iframe");

			iFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tap-card-iframe")));
			driver.switchTo().frame(iFrame);

			Thread.sleep(2000);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("tap-card-iframe-authentication")));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("challengeFrame")));

			Thread.sleep(3000);
			WebElement submitButton = driver.findElement(By.id("acssubmit"));
			submitButton.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollUpUntilEmenentFound() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -500);");

	}
}
