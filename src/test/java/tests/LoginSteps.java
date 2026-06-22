package tests;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * LoginSteps - shared login flow used by both LoginTest and DashboardTest.
 *
 * OTP entry: MANUAL via console (Scanner). This only works when the test
 * is run from a REAL terminal (e.g. IntelliJ's Terminal tab with
 * `mvn test -Dtest=tests.LoginTest`), NOT from the IDE's Run/TestNG tool
 * window - that window doesn't support stdin input, so typing won't work
 * there.
 *
 * TODO: once EmailOtpReader (IMAP) is ready, replace the Scanner block
 * below with: String otp = EmailOtpReader.getLatestOtp();
 */
public class LoginSteps {

    private static final String BASE_URL = "https://jobs.factnsoftware.com/";
    private static final String TEST_EMAIL = "abhimanigamage232@gmail.com"; // TODO: confirm test account email

    public static void loginAndReachDashboard(WebDriver driver, WebDriverWait wait) {

        // Step 1: Open the site
        driver.get(BASE_URL);

        // Step 2: Enter email
        WebElement emailBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailBox.clear();
        emailBox.sendKeys(TEST_EMAIL);

        // Step 3: Click Get OTP
        WebElement getOtpButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Get OTP')]")));
        getOtpButton.click();

        // Step 4: Wait for OTP screen
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@data-otp-index='0']")));

        // Step 5: Manual OTP entry (TODO: replace with EmailOtpReader once ready)
        System.out.println("========================================");
        System.out.println("Gmail check karala OTP eka methata type karanna:");
        System.out.println("========================================");

        Scanner scanner = new Scanner(System.in);
        String otp = scanner.nextLine().trim();

        // Step 6: Enter each digit
        for (int i = 0; i < 6; i++) {
            WebElement digitBox = driver.findElement(
                    By.xpath("//input[@data-otp-index='" + i + "']"));
            digitBox.clear();
            digitBox.sendKeys(String.valueOf(otp.charAt(i)));
        }

        // Step 7: Click Verify
        WebElement verifyButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Verify OTP')]")));
        verifyButton.click();

        // Step 8: Wait for dashboard to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Hiring operations overview')]")));
    }
}