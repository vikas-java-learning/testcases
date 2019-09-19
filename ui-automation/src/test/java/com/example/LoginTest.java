package com.example;

import com.example.pages.LoginPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test
    public void loginTest() {
        String username = "test1234z";
        String password = "Dummy1234";

        loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        assertThat(driver.getCurrentUrl(), equalTo("https://www.website.com/member/my-website/"));

    }
}
