package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(),'Admin Dashboard')]")
    WebElement dashboardHeader;

    public DashboardPage(WebDriver driver){

        super(driver);
    }

    public boolean isDashboardDisplayed(){

        return isDisplayed(dashboardHeader);
    }
}