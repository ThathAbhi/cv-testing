package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    WebElement emailTextbox;

    @FindBy(xpath = "//button[contains(text(),'Get OTP')]")
    WebElement continueButton;

    public LoginPage(WebDriver driver){

        super(driver);
    }

    public void enterEmail(String email){

        type(emailTextbox, email);
    }

    public void clickContinue(){

        click(continueButton);
    }

    public void requestOtp(String email){

        enterEmail(email);
        clickContinue();
    }
}