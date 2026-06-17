package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OtpPage;
import utils.ConfigReader;
import utils.EmailOtpReader;

public class LoginTest extends BaseTest {

    @Test(description = "Admin can log in using email + OTP and reach the dashboard")
    public void verifyAdminCanLoginSuccessfully() {

        String testEmail = ConfigReader.getProperty("email");

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.requestOtp(testEmail);

        EmailOtpReader otpReader =
                new EmailOtpReader();

        String otp =
                otpReader.getLatestOtp();

        OtpPage otpPage =
                new OtpPage(driver);

        otpPage.enterOtp(otp);
        otpPage.clickVerify();

        DashboardPage dashboardPage =
                new DashboardPage(driver);

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard is not displayed after OTP verification"
        );
    }
}