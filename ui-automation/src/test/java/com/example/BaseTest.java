package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Start");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("before test");
        System.setProperty("webdriver.chrome.driver", "/home/vikas-rajput/Documents/extreme-learning/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
    }

    @AfterTest
    public void afterTest() {
        driver.close();
        driver.quit();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("End");
    }
}
