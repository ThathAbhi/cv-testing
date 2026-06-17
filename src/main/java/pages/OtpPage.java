package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class OtpPage extends BasePage {

    @FindBy(xpath = "//input[@data-otp-index='0']")
    WebElement otpDigit0;

    @FindBy(xpath = "//input[@data-otp-index='1']")
    WebElement otpDigit1;

    @FindBy(xpath = "//input[@data-otp-index='2']")
    WebElement otpDigit2;

    @FindBy(xpath = "//input[@data-otp-index='3']")
    WebElement otpDigit3;

    @FindBy(xpath = "//input[@data-otp-index='4']")
    WebElement otpDigit4;

    @FindBy(xpath = "//input[@data-otp-index='5']")
    WebElement otpDigit5;

    @FindBy(xpath = "//button[contains(text(),'Verify OTP')]")
    WebElement verifyButton;

    public OtpPage(WebDriver driver){

        super(driver);
    }

    /**
     * The OTP page uses 6 separate single-digit boxes
     * (data-otp-index="0" through "5"), each with maxlength="1",
     * instead of one combined text field. This sends one digit
     * to each box in order.
     */
    public void enterOtp(String otp){

        if (otp == null || otp.length() != 6) {

            throw new IllegalArgumentException(
                    "OTP must be exactly 6 digits, but was: " + otp);
        }

        WebElement[] digitBoxes = {
                otpDigit0, otpDigit1, otpDigit2,
                otpDigit3, otpDigit4, otpDigit5
        };

        for (int i = 0; i < digitBoxes.length; i++) {

            type(digitBoxes[i], String.valueOf(otp.charAt(i)));
        }
    }

    public void clickVerify(){

        click(verifyButton);
    }
}