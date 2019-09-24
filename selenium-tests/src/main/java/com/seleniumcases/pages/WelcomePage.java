package com.seleniumcases.pages;

import com.seleniumcases.util.ApplicationProperties;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class WelcomePage extends BasePage {

    @FindBy ( linkText = "Welcome" )
    private WebElement welComeLink;

    @FindBy ( id = "wsb-button-00000000-0000-0000-0000-000450914890" )
    private WebElement herbalTeaBtn;

    @FindBy ( id = "wsb-button-00000000-0000-0000-0000-000451959280" )
    private WebElement checkOutGrnTBtn;

    @FindBy ( id = "email" )
    private WebElement emailField;

    @FindBy ( id = "name" )
    private WebElement nameField;

    @FindBy ( id = "address" )
    private WebElement addressField;

    @FindBy ( id = "card_type" )
    private WebElement cardTypeSelection;

    @FindBy ( id = "card_number" )
    private WebElement cardNumberField;

    @FindBy ( id = "cardholder_name" )
    private WebElement cardholderField;

    @FindBy ( id = "verification_code" )
    private WebElement verficationField;

    @FindBy ( className = "btn-primary" )
    private WebElement placeOrderBtn;

    private String url = ApplicationProperties.INSTANCE.getBaseUrl ();

    public WelcomePage ( WebDriver driver ) {
        super ( driver );
        PageFactory.initElements ( getWebDriver (), this );
    }

}
