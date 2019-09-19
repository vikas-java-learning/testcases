package com.example;

import com.example.pages.SignUpPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SignUpTest extends BaseTest {

    private SignUpPage signUpPage;

    @Test
    public void signUpClick() {

        signUpPage = new SignUpPage(driver);
        signUpPage.signUpClick();

        assertThat(driver.getCurrentUrl(), equalTo(signUpPage.getUrl() + "sign-up/"));
    }

    @Test
    public void fillRegistationForm() {
        signUpPage = new SignUpPage(driver);
        signUpPage.signUpClick();
        signUpPage.freePlanButtonClick();
        signUpPage.fillSignUpForm();

        assertThat(driver.getTitle(), equalTo("Get Started | Build a Free Website | Website.com"));
    }

}
