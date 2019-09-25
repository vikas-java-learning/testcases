package com.seleniumcases;

import com.seleniumcases.util.ApplicationProperties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest_SingleBrowser {

    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite () {
        System.out.println ( "Start" );
    }

    private void createSauceDriver ( String methodName ) {
        System.out.println ( "before test" );
        /**
         * In this section, we will configure our SauceLabs credentials in order to run our tests on saucelabs.com
         */
        String sauceUserName = "vikas_111077";
        String sauceAccessKey = "42312f81-fdb3-4ac0-9519-d106ab95603a";

        /**
         * In this section, we will configure our test to run on some specific
         * browser/os combination in Sauce Labs
         */
        DesiredCapabilities capabilities = new DesiredCapabilities ();

        //set your user name and access key to run tests in Sauce
        capabilities.setCapability ( "username", sauceUserName );

        //set your sauce labs access key
        capabilities.setCapability ( "accessKey", sauceAccessKey );

        //set browser to Safari
        capabilities.setCapability ( "browserName", "Safari" );

        //set operating system to macOS version 10.13
        capabilities.setCapability ( "platform", "macOS 10.13" );

        //set the browser version to 11.1
        capabilities.setCapability ( "version", "11.1" );

        //set the build name of the application
        capabilities.setCapability ( "build", "Selenium testcases" );

        //set your test case name so that it shows up in Sauce Labs
        capabilities.setCapability ( "name", methodName );

        try {
            driver = new RemoteWebDriver ( new URL ( "https://ondemand.saucelabs.com/wd/hub" ), capabilities );
        } catch (MalformedURLException e) {
            throw new RuntimeException ( e );
        }

        //navigate to the url of the Sauce Labs Sample app
        driver.navigate ().to ( ApplicationProperties.INSTANCE.getBaseUrl () );
    }

    @BeforeMethod
    protected void createDriver ( Method method ) {
        String methodName = method.getName ();
        createSauceDriver ( methodName );
    }

    //Method that gets invoked after test
    @AfterMethod
    public void tearDown ( ITestResult result ) {
        try {
            ((JavascriptExecutor) driver).executeScript ( "sauce:job-result=" + (result.isSuccess () ? "passed" : "failed") );
        } finally {
            driver.quit ();
        }
    }

    @AfterSuite
    public void afterSuite () {
        System.out.println ( "End" );
    }
}
