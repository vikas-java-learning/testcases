package com.seleniumcases.pages;

import com.seleniumcases.util.ApplicationProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class WelcomePage extends BasePage {

    @FindBy(linkText = "Welcome")
    WebElement welComeLink;

    @FindBy(id = "wsb-button-00000000-0000-0000-0000-000450914890")
    WebElement herbalTeaBtn;

    @FindBy(id = "wsb-button-00000000-0000-0000-0000-000451959280")
    WebElement checkOutGrnTBtn;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "name")
    WebElement nameField;

    @FindBy(id = "address")
    WebElement addressField;

    @FindBy(id = "card_type")
    WebElement cardTypeSelection;

    @FindBy(id = "card_number")
    WebElement cardNumberField;

    @FindBy(id="cardholder_name")
    WebElement cardholderField;

    @FindBy(id = "verification_code")
    WebElement verficationField;

    @FindBy(className = "btn-primary")
    WebElement placeOrderBtn;

    private String url = ApplicationProperties.INSTANCE.getBaseUrl();

    public WelcomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getWebDriver(), this);
        getWebDriver().get(url);
    }

    public void clickOnWelcome()
    {
        welComeLink.click();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void clickOnHerbalTeaMenuBtn()
    {
        herbalTeaBtn.click();
    }

    public void checkOutBtn()
    {
        checkOutGrnTBtn.click();
    }

    public void fillCustomerInfo(String email, String name, String address)
    {
        emailField.sendKeys(email);
        nameField.sendKeys(name);
        addressField.sendKeys(address);
    }

    public void fillPaymentInfo(String cardType, String cardNum, String cardHolderName, String verifyCode)
    {
        Select cardSelection = new Select(cardTypeSelection);
        cardSelection.selectByVisibleText(cardType);

        cardNumberField.sendKeys(cardNum);
        cardholderField.sendKeys(cardHolderName);
        verficationField.sendKeys(verifyCode);
    }

    public void placeOrderBtn()
    {
        placeOrderBtn.click();
    }

}
