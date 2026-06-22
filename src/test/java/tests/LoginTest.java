package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.DashboardPage;

/**
 * LoginTest - verifies the full login -> OTP -> dashboard flow.
 *
 * Login steps live in LoginSteps.java (shared with DashboardTest) so the
 * flow isn't duplicated. OTP entry is currently MANUAL via console -
 * when this test runs, check the terminal: it will pause and wait for you
 * to type the OTP digits and press Enter. Once EmailOtpReader is ready,
 * only LoginSteps.java needs to change - this class stays the same.
 */
public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void verifyLoginReachesDashboard() {
        LoginSteps.loginAndReachDashboard(driver, wait);

        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardLoaded(),
                "Dashboard did not load after login - expected the 'Hiring operations overview' header to be visible");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}