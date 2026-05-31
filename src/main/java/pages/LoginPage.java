package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final String URL = "http://localhost:5173/login";

    // Locator
    private final By usernameField = By.id("username");

    private final By passwordField = By.id("password");

    private final By loginButton = By.cssSelector(".login-button");

    private final By errorMessage = By.cssSelector(".login-error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Buka halaman login
    public void open() {
        driver.get(URL);
    }

    // Input username
    public void inputUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    // Input password
    public void inputPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    // Klik tombol login
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Login sekaligus
    public void login(String username, String password) {

        inputUsername(username);

        inputPassword(password);

        clickLoginButton();
    }

    // Cek masih di halaman login
    public boolean isOnLoginPage() {

        return driver.getCurrentUrl().contains("/login");
    }

    // Cek pesan error login
    public boolean isErrorMessageDisplayed() {

        try {

            return driver.findElement(errorMessage).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    // Cek field username tampil
    public boolean isUsernameFieldVisible() {

        try {

            return driver.findElement(usernameField).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    // Cek field password tampil
    public boolean isPasswordFieldVisible() {

        try {

            return driver.findElement(passwordField).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    // Tunggu proses login
    public void waitForSuccessfulLogin() throws InterruptedException {

        Thread.sleep(3000);
    }
}