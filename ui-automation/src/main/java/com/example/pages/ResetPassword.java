package com.example.pages;

import com.example.utils.ApplicationProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ResetPassword extends BasePage {

    private String url = ApplicationProperties.INSTANCE.getBaseUrl() + "member/my-account/login-info/";

    public ResetPassword(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getWebDriver(), this);
        getWebDriver().get(url);
    }
}
