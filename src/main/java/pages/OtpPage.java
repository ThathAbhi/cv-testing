package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OtpPage {

    WebDriver driver;

    @FindBy(id = "otp")
    WebElement otpTextbox;

    @FindBy(xpath = "//button[contains(text(),'Verify OTP')]")
    WebElement verifyButton;

    public OtpPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterOtp(String otp){

        otpTextbox.sendKeys(otp);
    }

    public void clickVerify(){

        verifyButton.click();
    }
}
