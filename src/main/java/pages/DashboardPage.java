package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // ---------- Header ----------

    @FindBy(xpath = "//h1[contains(text(),'Hiring operations overview')]")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//button[contains(text(),'Create Job')]")
    private WebElement createJobButton;

    @FindBy(xpath = "//button[contains(text(),'Logout')]")
    private WebElement logoutButton;

    // ---------- Stat Cards ----------

    @FindBy(xpath = "//p[text()='Admins']/following-sibling::p")
    private WebElement adminsCountValue;

    @FindBy(xpath = "//p[text()='Candidates']/following-sibling::p")
    private WebElement candidatesCountValue;

    @FindBy(xpath = "//p[text()='Jobs']/following-sibling::p")
    private WebElement jobsCountValue;

    @FindBy(xpath = "//p[text()='Applications']/following-sibling::p")
    private WebElement applicationsCountValue;

    // ---------- Quick Links ----------
    // ancestor::a[1] = closest/nearest <a> ancestor (the card link itself),
    // NOT the outer sidebar nav <a> elements which were being clicked before.


    @FindBy(xpath = "//a[@href='/admin/jobs']")
    private WebElement manageJobsLink;

    @FindBy(xpath = "//a[@href='/admin/candidates']")  // TODO: confirm real href
    private WebElement candidateDirectoryLink;

    @FindBy(xpath = "//a[@href='/admin/settings']")    // TODO: confirm real href
    private WebElement adminSettingsLink;

    @FindBy(xpath = "//a[@href='/admin/audit']")       // TODO: confirm real href
    private WebElement auditTrailLink;
    // ---------- Recent Candidates Section ----------

    @FindBy(xpath = "//h2[text()='Latest activity']")
    private WebElement recentCandidatesSectionHeader;

    @FindBy(xpath = "//a[text()='View all']")
    private WebElement viewAllButton;

    @FindBy(xpath = "(//div[@class='mt-5 space-y-3'])/a")
    private List<WebElement> recentCandidateRows;

    // ================== Actions / Verifications ==================

    public boolean isDashboardLoaded() {
        return wait.until(ExpectedConditions.visibilityOf(dashboardHeader)).isDisplayed();
    }

    public int getAdminsCount() {
        return Integer.parseInt(
                wait.until(ExpectedConditions.visibilityOf(adminsCountValue)).getText().trim());
    }

    public int getCandidatesCount() {
        return Integer.parseInt(
                wait.until(ExpectedConditions.visibilityOf(candidatesCountValue)).getText().trim());
    }

    public int getJobsCount() {
        return Integer.parseInt(
                wait.until(ExpectedConditions.visibilityOf(jobsCountValue)).getText().trim());
    }

    public int getApplicationsCount() {
        return Integer.parseInt(
                wait.until(ExpectedConditions.visibilityOf(applicationsCountValue)).getText().trim());
    }

    public void clickManageJobs() {
        wait.until(ExpectedConditions.elementToBeClickable(manageJobsLink)).click();
    }

    public void clickCandidateDirectory() {
        wait.until(ExpectedConditions.elementToBeClickable(candidateDirectoryLink)).click();
    }

    public void clickAdminSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsLink)).click();
    }

    public void clickAuditTrail() {
        wait.until(ExpectedConditions.elementToBeClickable(auditTrailLink)).click();
    }

    public boolean isViewAllButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(viewAllButton)).isDisplayed();
    }

    public void clickViewAll() {
        wait.until(ExpectedConditions.elementToBeClickable(viewAllButton)).click();
    }

    public int getRecentCandidatesCount() {
        wait.until(ExpectedConditions.visibilityOf(recentCandidatesSectionHeader));
        return recentCandidateRows.size();
    }

    public String getCandidateNameAt(int index) {
        WebElement row = recentCandidateRows.get(index);
        return row.findElement(By.xpath(".//h3")).getText();
    }

    public String getCandidateEmailAt(int index) {
        WebElement row = recentCandidateRows.get(index);
        return row.findElement(By.xpath(".//p")).getText();
    }
}