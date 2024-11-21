package com.example.testbase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class webUtils {

    private static webUtils webInstance = null;

    public synchronized static webUtils getInstance() {
        if (webInstance == null) {
            webInstance = new webUtils();
        }
        return webInstance;
    }

    public synchronized WebDriver webDriverInit() {
        String browser = "edge"; //temporarily hard coded, TODO: value to be retrieved from globalSettings file
        WebDriver driver = null;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        return driver;
    }

    public void launchWebPage(String url) {
        try {
            WebDriver driver = webFactory.getDriver();
            driver.get(url);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void closeBrowser(){
        webFactory.getDriver().quit();
        webFactory.removeDriver();
    }

    public void waitforPagetoLoad(){
        WebDriver driver = webFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public String getTitle(){
        WebDriver driver = webFactory.getDriver();
        return driver.getTitle();
    }

    public String getElementTextByXpath(String xpath){
        WebDriver driver = webFactory.getDriver();
        WebElement element = driver.findElement(By.xpath(xpath));
        return element.getText();
    }

    public Boolean elementIsVisible(String xpath){
        WebDriver driver = webFactory.getDriver();
        WebElement element = driver.findElement(By.xpath(xpath));
        return element.isDisplayed();
    }

    public void elementClick(String xpath){
        WebDriver driver = webFactory.getDriver();
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }

}



