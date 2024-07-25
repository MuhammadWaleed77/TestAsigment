package com.tap.pages;


import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.tap.utils.Log;

public class HomePage extends BasePage {
	
	 public HomePage(WebDriver driver) {
	        super(driver);
	    }

    @FindBy(id = "currency")
    private WebElement currencyDropdown;

    @FindBy(xpath = "//option[contains(text(),'BHD')]")
    private WebElement bhdOption;

    @FindBy(id = "scope")
    private WebElement scopeDropdown;

    @FindBy(xpath = "//option[contains(text(),'Authenticated Token')]")
    private WebElement authenticatedTokenOption;

    @FindBy(id = "card_input_mini")
    private WebElement cardNumberInput;

    @FindBy(id = "date_input")
    private WebElement expiryDateInput;

    @FindBy(id = "cvv_input")
    private WebElement cvvInput;

    @FindBy(xpath = "//button[.='Create Token']")
    private WebElement generateTokenButton;
    
    @FindBy(id = "tap-card-iframe")
    private WebElement iframe;
    
    

   

    public void selectCurrency(String currency)  {
        Log.info("Selecting currency: " + currency);
     //   currencyDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(bhdOption));
       // Thread.sleep(1000);
       // bhdOption.click();
        
        Select select = new Select(currencyDropdown);
        select.selectByVisibleText(currency);
        
     
    }

    public void selectScope(String scope) {
        Log.info("Selecting scope: " + scope);
       // scopeDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(scopeDropdown));
        Select select = new Select(scopeDropdown);
        select.selectByVisibleText(scope);
    }

    public void enterCardDetails(String cardNumber, String expiryDate, String cvv) {
        Log.info("Entering card details");
        cardNumberInput.sendKeys(cardNumber);
        expiryDateInput.sendKeys(expiryDate);
        cvvInput.sendKeys(cvv);
    }

    public void clickGenerateToken() {
        Log.info("Clicking Generate Token button");
        generateTokenButton.click();
    }
    
    public void swithchToIframe() {
        Log.info("Switching to Iframe");
        driver.switchTo().frame(iframe);
        try {
            
             cardNumberInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='card_input_mini']")));
         
             
             
            if (cardNumberInput.isDisplayed()) {
                System.out.println("Successfully switched to the iframe and found the element.");
            }
        } catch (NoSuchElementException e) {
            
            System.out.println("Failed to switch to the iframe.");
        }
    }
    
    public void scrollUntilElementFound() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isElementFound = false;

        while (!isElementFound) {
            try {
               
                js.executeScript("window.scrollBy(0, 500);"); 

                
                wait.until(ExpectedConditions.visibilityOf(generateTokenButton));
                isElementFound = true; 
            } catch (Exception e) {
                
                System.out.println("Element not found, scrolling down...");
            }
        }
    }
    
    
}

