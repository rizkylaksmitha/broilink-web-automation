package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

    private final String URL = "http://localhost:5173/";

    private By logo =
            By.className("logo-icon");

    private By navbar =
            By.className("nav-desktop");

    private By loginButton =
            By.xpath("//button[contains(text(),'Gabung')]");

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("5173");
    }

    public boolean isLogoDisplayed() {

        try {
            return driver.findElement(logo).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNavbarDisplayed() {

        try {
            return driver.findElement(navbar).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNavbarVisible() {
        return isNavbarDisplayed();
    }

    public boolean isLoginButtonVisible() {

        try {
            return driver.findElement(loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickGabungButton() {
        driver.findElement(loginButton).click();
    }

    public LoginPage clickLogin() {
        clickGabungButton();
        return new LoginPage(driver);
    }
}