package com.seleniumcases;

import com.seleniumcases.pages.WelcomePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WelcomeTest extends BaseTest {

    private WelcomePage welcomePage;

    @Test
    public void clickOnWelcomeLink() {
        welcomePage = new WelcomePage(driver);
        welcomePage.clickOnWelcome();
        assertThat(driver.getCurrentUrl(), equalTo(welcomePage.getUrl() + "welcome.html"));
    }

    @Test
    public void herbalTeaMenuItems() {
        welcomePage = new WelcomePage(driver);
        welcomePage.clickOnWelcome();
        welcomePage.clickOnHerbalTeaMenuBtn();
        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertThat(true, equalTo(bodyText.contains("Red Tea")));
    }

    @Test
    public void checkOutItem() {
        welcomePage = new WelcomePage(driver);
        welcomePage.clickOnWelcome();
        welcomePage.clickOnHerbalTeaMenuBtn();
        welcomePage.checkOutBtn();

        assertThat(driver.getTitle(), equalTo("Check Out"));
    }

    @Test
    public void placeOrder() {
        welcomePage = new WelcomePage(driver);
        welcomePage.clickOnWelcome();
        welcomePage.clickOnHerbalTeaMenuBtn();
        welcomePage.checkOutBtn();
        welcomePage.fillCustomerInfo("demo@gmail.com", "TestName", "virar mumbai");
        welcomePage.fillPaymentInfo("Mastercard", "5105 1051 0510 5100", "TestName", "12345");

        welcomePage.placeOrderBtn();

        assertThat(driver.getCurrentUrl(), equalTo(welcomePage.getUrl() + "menu.html?"));
    }

}
