package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashboardPage;

/**
 * DashboardTest - covers the dashboard test cases that can be verified
 * purely through the UI (TC-DASH-001/002-005/007-009/012-017/023/024 -
 * the ones that don't need extra data setup, see chat notes for the rest).
 *
 * IMPORTANT / TODO:
 * - Login uses LoginSteps.loginAndReachDashboard(), which currently does
 *   MANUAL OTP entry via console (same as LoginTest.java). When you run
 *   this test class, check the console/terminal output - it will pause
 *   and wait for you to type the OTP digit string and press Enter.
 *   Once EmailOtpReader is ready, only LoginSteps.java needs to change.
 * - Quick-link URL assertions (e.g. "/admin/jobs") are DUMMY guesses.
 *   Replace with the real routes once confirmed.
 * - All locators live inside DashboardPage - fix those first.
 */
public class DashboardTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private DashboardPage dashboardPage;

    private static final String DASHBOARD_URL = "https://jobs.factnsoftware.com/admin"; // TODO: confirm

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Logs in via email + manual OTP entry (console prompt) and lands on
        // the dashboard. Replace with EmailOtpReader inside LoginSteps once ready.
        LoginSteps.loginAndReachDashboard(driver, wait);
    }

    @BeforeMethod
    public void goToDashboard() {
        // Re-navigate to dashboard before each test so navigation tests
        // (quick links / view all) don't leave subsequent tests stranded
        // on a different page.
        driver.get(DASHBOARD_URL);
        dashboardPage = new DashboardPage(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC-DASH-001
    @Test
    public void verifyDashboardLoadsSuccessfully() {
        Assert.assertTrue(dashboardPage.isDashboardLoaded(),
                "Dashboard header was not displayed after login");
    }

    // TC-DASH-002
    @Test
    public void verifyTotalAdminsCountIsDisplayed() {
        int adminsCount = dashboardPage.getAdminsCount();
        Assert.assertTrue(adminsCount >= 0,
                "Admins count should be a valid non-negative number");
    }

    // TC-DASH-003
    @Test
    public void verifyTotalCandidatesCountIsDisplayed() {
        int candidatesCount = dashboardPage.getCandidatesCount();
        Assert.assertTrue(candidatesCount >= 0,
                "Candidates count should be a valid non-negative number");
    }

    // TC-DASH-004
    @Test
    public void verifyTotalJobsCountIsDisplayed() {
        int jobsCount = dashboardPage.getJobsCount();
        Assert.assertTrue(jobsCount >= 0,
                "Jobs count should be a valid non-negative number");
    }

    // TC-DASH-005
    @Test
    public void verifyTotalApplicationsCountIsDisplayed() {
        int applicationsCount = dashboardPage.getApplicationsCount();
        Assert.assertTrue(applicationsCount >= 0,
                "Applications count should be a valid non-negative number");
    }

    // TC-DASH-007
    @Test
    public void verifyRecentCandidatesShowsMaxFiveByDefault() {
        int rowCount = dashboardPage.getRecentCandidatesCount();
        Assert.assertTrue(rowCount <= 5,
                "Recent Candidates section should show a maximum of 5 records by default, but found: " + rowCount);
    }

    // TC-DASH-008
    @Test
    public void verifyCandidateNameIsDisplayed() {
        Assert.assertTrue(dashboardPage.getRecentCandidatesCount() > 0,
                "No recent candidates found to verify name against");
        String name = dashboardPage.getCandidateNameAt(0);
        Assert.assertFalse(name.trim().isEmpty(), "Candidate name should not be empty");
    }

    // TC-DASH-009
    @Test
    public void verifyCandidateEmailIsDisplayed() {
        Assert.assertTrue(dashboardPage.getRecentCandidatesCount() > 0,
                "No recent candidates found to verify email against");
        String email = dashboardPage.getCandidateEmailAt(0);
        Assert.assertTrue(email.contains("@"), "Candidate email should be a valid-looking email: " + email);
    }

    // TC-DASH-012
    @Test
    public void verifyViewAllButtonIsDisplayed() {
        Assert.assertTrue(dashboardPage.isViewAllButtonDisplayed(), "View All button should be visible");
    }

    // TC-DASH-013
    @Test
    public void verifyViewAllNavigatesToCandidateDirectory() {
        dashboardPage.clickViewAll();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://jobs.factnsoftware.com/admin/candidates"), // TODO: confirm real route
                "Clicking View All should navigate to the Candidate Directory page. Actual URL: "
                        + driver.getCurrentUrl());
    }

    // TC-DASH-014
    @Test
    public void verifyManageJobsQuickLinkNavigation() {
        dashboardPage.clickManageJobs();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://jobs.factnsoftware.com/admin/jobs"), // TODO: confirm real route
                "Manage Jobs quick link should navigate to the Manage Jobs page. Actual URL: "
                        + driver.getCurrentUrl());
    }

    // TC-DASH-015
    @Test
    public void verifyCandidateDirectoryQuickLinkNavigation() {
        dashboardPage.clickCandidateDirectory();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://jobs.factnsoftware.com/admin/candidates"), // TODO: confirm real route
                "Candidate Directory quick link should navigate to the Candidate Directory page. Actual URL: "
                        + driver.getCurrentUrl());
    }

    // TC-DASH-016
    @Test
    public void verifyAdminSettingsQuickLinkNavigation() {
        dashboardPage.clickAdminSettings();
        Assert.assertTrue(driver.getCurrentUrl().contains("/admin/settings"), // TODO: confirm real route
                "Admin Settings quick link should navigate to the Admin Settings page. Actual URL: "
                        + driver.getCurrentUrl());
    }

    // TC-DASH-017
    @Test
    public void verifyAuditTrailQuickLinkNavigation() {
        dashboardPage.clickAuditTrail();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://jobs.factnsoftware.com/admin/audit"), // TODO: confirm real route
                "Audit Trail quick link should navigate to the Audit Trail page. Actual URL: "
                        + driver.getCurrentUrl());
    }

    // TC-DASH-023
    @Test
    public void verifyDashboardLoadsCorrectlyAfterRefresh() {
        driver.navigate().refresh();
        Assert.assertTrue(dashboardPage.isDashboardLoaded(),
                "Dashboard should reload correctly after refresh");
    }

    // TC-DASH-024
    @Test
    public void verifyAllDashboardCardsAndSectionsVisible() {
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard header should be visible");
        Assert.assertTrue(dashboardPage.getAdminsCount() >= 0, "Admins card should be visible");
        Assert.assertTrue(dashboardPage.getCandidatesCount() >= 0, "Candidates card should be visible");
        Assert.assertTrue(dashboardPage.getJobsCount() >= 0, "Jobs card should be visible");
        Assert.assertTrue(dashboardPage.getApplicationsCount() >= 0, "Applications card should be visible");
        Assert.assertTrue(dashboardPage.isViewAllButtonDisplayed(), "Recent Candidates section should be visible");
    }
}