package com.tap.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.tap.utils.Log;

public class ResponsePage extends BasePage {

    @FindBy(xpath="//input[@value='response']")
    private WebElement responseTab;

    @FindBy(xpath = "//pre[@data-testid='CodeBlock-content']")
    private WebElement responseData;

    public ResponsePage(WebDriver driver) {
        super(driver);
    }
    public void clickResponseSection() throws InterruptedException {
        Log.info("Clicking Response Section");
        Thread.sleep(3000);
        System.out.println("Clicking Response Section");
        responseTab.click();
        System.out.println("Response Section DONE");
    }

    public String getResponseData() throws InterruptedException {
        Log.info("Getting response data");
        Thread.sleep(1000);
        String jsonData = responseData.getText();
        return responseData.getText();
        
    }
    
    
}

