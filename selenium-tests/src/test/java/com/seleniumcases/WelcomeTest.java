package com.seleniumcases;

import com.seleniumcases.pages.WelcomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WelcomeTest extends BaseTest {

    private WelcomePage welcomePage;


    @Test
    public void clickOnWelcomeLink () {
        welcomePage = new WelcomePage ( driver );
        welcomePage.getWelComeLink ().click ();
        assertThat ( "http://www.practiceselenium.com/welcome.html", equalTo ( welcomePage.getUrl () + "welcome.html" ) );
    }

    @Test
    public void herbalTeaMenuItems () {
        welcomePage = new WelcomePage ( driver );
        welcomePage.getWelComeLink ().click ();
        welcomePage.getHerbalTeaBtn ().click ();
        String bodyText = driver.findElement ( By.tagName ( "body" ) ).getText ();
        assertThat ( true, equalTo ( bodyText.contains ( "Red Tea" ) ) );
    }

    @Test
    public void checkOutItem () {
        welcomePage = new WelcomePage ( driver );
        welcomePage.getWelComeLink ().click ();
        welcomePage.getHerbalTeaBtn ().click ();
        welcomePage.getCheckOutGrnTBtn ().click ();
        String bodyText = driver.findElement ( By.tagName ( "body" ) ).getText ();
        assertThat ( true, equalTo ( bodyText.contains ( "Pay with Credit" ) ) );
    }

    @Test
    public void placeOrder () {
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
