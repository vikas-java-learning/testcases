package com.seleniumcases;

import com.seleniumcases.pages.WelcomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WelcomeTest_MultipleBrowser extends BaseTest_MultiBrowser {

    private WelcomePage welcomePage;


    @Test(dataProvider = "sauceBrowsers")
    public void clickOnWelcomeLink (String browser, String browserVersion, String platformName) {
        this.createDriver(browser, browserVersion, platformName, "clickOnWelcomeLink");
        welcomePage = new WelcomePage ( driver );
        welcomePage.getWelComeLink ().click ();
        assertThat ( "http://www.practiceselenium.com/welcome.html", equalTo ( welcomePage.getUrl () + "welcome.html" ) );
    }

    @Test(dataProvider = "sauceBrowsers")
    public void herbalTeaMenuItems (String browser, String browserVersion, String platformName) {
        this.createDriver(browser, browserVersion, platformName, "herbalTeaMenuItems");
        welcomePage = new WelcomePage ( driver );
        welcomePage.getWelComeLink ().click ();
        welcomePage.getHerbalTeaBtn ().click ();
        String bodyText = driver.findElement ( By.tagName ( "body" ) ).getText ();
        assertThat ( true, equalTo ( bodyText.contains ( "Red Tea" ) ) );
    }

    @Test(dataProvider = "sauceBrowsers")
    public void checkOutItem (String browser, String browserVersion, String platformName) {
        this.createDriver(browser, browserVersion, platformName, "checkOutItem");
        welcomePage = new WelcomePage ( driver );
        welcomePage.getWelComeLink ().click ();
        welcomePage.getHerbalTeaBtn ().click ();
        welcomePage.getCheckOutGrnTBtn ().click ();
        String bodyText = driver.findElement ( By.tagName ( "body" ) ).getText ();
        assertThat ( true, equalTo ( bodyText.contains ( "Pay with Credit" ) ) );
    }

    @Test(dataProvider = "sauceBrowsers")
    public void placeOrder (String browser, String browserVersion, String platformName) {
        this.createDriver(browser, browserVersion, platformName, "placeOrder");
        welcomePage = new WelcomePage ( driver );
        welcomePage.getWelComeLink ().click ();
        welcomePage.getHerbalTeaBtn ().click ();
        welcomePage.getCheckOutGrnTBtn ().click ();
        welcomePage.getEmailField ().sendKeys ( "demo@gmail.com" );
        welcomePage.getNameField ().sendKeys ( "TestName" );
        welcomePage.getAddressField ().sendKeys ( "virar mumbai" );
        Select card_selection = new Select ( welcomePage.getCardTypeSelection () );
        card_selection.selectByVisibleText ( "Mastercard" );
        welcomePage.getCardNumberField ().sendKeys ( "5105 1051 0510 5100" );
        welcomePage.getCardholderField ().sendKeys ( "TestName" );
        welcomePage.getVerficationField ().sendKeys ( "12345" );
        welcomePage.getPlaceOrderBtn ().click ();
        assertThat ( "http://www.practiceselenium.com/menu.html?", equalTo ( welcomePage.getUrl () + "menu.html?" ) );
    }

}
