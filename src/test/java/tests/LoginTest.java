package tests;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {

            // Step 1: Open the site
            driver.get("https://jobs.factnsoftware.com/");
            driver.manage().window().maximize();

            // Step 2: Enter email
            WebElement emailBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("email")));

            emailBox.clear();
            emailBox.sendKeys("abhimanigamage232@gmail.com");

            // Step 3: Click Get OTP
            WebElement getOtpButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(text(),'Get OTP')]")));

            getOtpButton.click();

            // Step 4: Wait for OTP screen
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@data-otp-index='0']")));

            // Step 5: Ask YOU to type OTP
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

            // Step 8: Check dashboard
            WebElement dashboardHeader = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[contains(text(),'Hiring operations overview')]")));

            if (dashboardHeader.isDisplayed()) {
                System.out.println("SUCCESS - Dashboard load wuna!");
            }

        } catch (Exception e) {

            System.out.println("FAILED: " + e.getMessage());
            e.printStackTrace();

        }
    }
}