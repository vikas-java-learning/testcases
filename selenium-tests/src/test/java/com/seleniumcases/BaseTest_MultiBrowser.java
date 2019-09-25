package com.seleniumcases;

import com.seleniumcases.util.ApplicationProperties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest_MultiBrowser {

    protected WebDriver driver;
    private ThreadLocal<String> sessionId = new ThreadLocal<>();
    @BeforeSuite
    public void beforeSuite () {
        System.out.println ( "Start" );
    }

    private void createSauceDriver ( MutableCapabilities capabilities, String methodName ) {
        System.out.println ( "before test" );
        /**
         * In this section, we will configure our SauceLabs credentials in order to run our tests on saucelabs.com
         */
        String sauceUserName = "vikas_111077";
        String sauceAccessKey = "42312f81-fdb3-4ac0-9519-d106ab95603a";

        //Create a map of capabilities called "sauce:options", which contain the info necessary to run on Sauce
        // Labs, using the credentials stored in the environment variables. Also runs using the new W3C standard.
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", sauceUserName);
        sauceOptions.setCapability("accessKey", sauceAccessKey);
        sauceOptions.setCapability("seleniumVersion", "3.141.59");
        sauceOptions.setCapability("name", methodName);
        sauceOptions.setCapability("build", "parallel-TestNG-cross-browser-and-platform-demo");

        //Assign the Sauce Options to the base capabilities
        capabilities.setCapability("sauce:options", sauceOptions);

        try {
            driver = new RemoteWebDriver ( new URL ( "https://ondemand.saucelabs.com/wd/hub" ), capabilities );
        } catch (MalformedURLException e) {
            throw new RuntimeException ( e );
        }
        sessionId.set(((RemoteWebDriver)driver).getSessionId().toString());

        // set current sessionId
        String id = ((RemoteWebDriver) driver).getSessionId().toString();
        sessionId.set(id);
        //navigate to the url of the Sauce Labs Sample app
        driver.get ( ApplicationProperties.INSTANCE.getBaseUrl () );
    }

    protected void createDriver(String browser, String browserVersion, String platformName, String methodName) {
        //Set up the ChromeOptions object, which will store the capabilities
        MutableCapabilities capabilities = new MutableCapabilities();

        if (browser.equals("chrome")) {
            ChromeOptions caps = new ChromeOptions();
            caps.setExperimentalOption("w3c", true);
            capabilities = caps;
        }
        else if (browser.equals("firefox")) {
            capabilities = new FirefoxOptions ();
        }
        else if (browser.equals ( "internet explorer" ))
        {
            capabilities = new InternetExplorerOptions (  );
        }

        capabilities.setCapability("browserVersion", browserVersion);
        capabilities.setCapability("platformName", platformName);

                createSauceDriver(capabilities, methodName);


    }

    /**
     * DataProvider that sets the browser combinations to be used.
     *
     * @param beforeMethod
     * @return TestNG's preferred Object[][] structure, containing browser, version, and platform information
     */
    @DataProvider (name = "sauceBrowsers", parallel = false)
    public static Object[][] sauceBrowserDataProvider(Method beforeMethod) {
        return new Object[][]{
                /** Uncomment the other data providers ONLY if you have the relevant Sauce VM concurrency **/
//                new Object[]{"chrome", "73.0", "macOS 10.14"},
                new Object[]{"internet explorer", "11.0", "Windows 8.1"},
                /*new Object[]{"chrome", "72.0", "Windows 10"},
                new Object[]{"chrome", "71.0", "Windows 7"},
                new Object[]{"chrome", "70.0", "macOS 10.14"},
                new Object[]{"chrome", "70.0", "macOS 10.14"},
                new Object[]{"chrome", "71.0", "Windows 10"},
                new Object[]{"chrome", "72.0", "Windows 7"},
                new Object[]{"chrome", "73.0", "macOS 10.14"},
                new Object[]{"chrome", "72.0", "macOS 10.14"},
                new Object[]{"chrome", "73.0", "Windows 10"},
                new Object[]{"chrome", "70.0", "Windows 7"},
                new Object[]{"chrome", "71.0", "macOS 10.14"},
                new Object[]{"firefox", "65.0", "Windows 10"},
                new Object[]{"firefox", "64.0", "macOS 10.14"},
                new Object[]{"firefox", "63.0", "macOS 10.13"},
                new Object[]{"firefox", "62.0", "macOS 10.12"},
                new Object[]{"firefox", "61.0", "macOS 10.13"},*/
        };
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
