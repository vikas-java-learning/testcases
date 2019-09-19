package com.example.pages;

import com.example.utils.ApplicationProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends BasePage {

    @FindBy(id = "menuSignup")
    private WebElement signupLink;

    @FindBy(xpath = "//*[@id=\"P4\"]/div[1]/button")
    private WebElement freePlanButton;

    @FindBy(id = "fa-firstname")
    private WebElement firstName;

    @FindBy(id = "fa-lastname")
    private WebElement lastName;

    @FindBy(id = "fa-email")
    private WebElement email;

    @FindBy(id = "fa-username")
    private WebElement username;

    @FindBy(id = "fa-password")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"subsection1\"]/li[6]/button")
    private WebElement signUpClickBtn;

    private String url = ApplicationProperties.INSTANCE.getBaseUrl();

    public SignUpPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getWebDriver(), this);
        getWebDriver().get(url);
    }

    public void signUpClick() {
//        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(signupButton)).click();
        signupLink.click();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void freePlanButtonClick() {
        freePlanButton.click();
    }

    public void fillSignUpForm() {
        String _firstName = "Vikas";
        String _lastName = "Singh";
        String _email = "vikas_111077@rediffmail.com";
        String _username = "vikas111555";
        String _password = "12345678G";

        firstName.sendKeys(_firstName);
        lastName.sendKeys(_lastName);
        email.sendKeys(_email);
        username.sendKeys(_username);
        password.sendKeys(_password);

        signUpClickBtn.click();
    }
}
