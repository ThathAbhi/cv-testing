package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OtpPage;
import utils.EmailOtpReader;

public class LoginTest extends BaseTest {

    @Test
    public void verifyAdminCanLoginSuccessfully() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.requestOtp(
                "admin@test.com");

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
                "Dashboard is not displayed"
        );
    }
}