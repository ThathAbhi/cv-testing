package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(id = "email")
    WebElement emailTextbox;

    @FindBy(xpath = "//button[contains(text(),'Get OTP')]")
    WebElement continueButton;

    public LoginPage(WebDriver driver){

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email){

        emailTextbox.clear();
        emailTextbox.sendKeys(email);
    }

    public void clickContinue(){

        continueButton.click();
    }

    public void requestOtp(String email){

        enterEmail(email);
        clickContinue();
    }
}
