package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By txtUsername = By.xpath("//input[@type='text' or @placeholder='Username' or contains(@name,'username')]");
    private By txtPassword = By.xpath("//input[@type='password' or @placeholder='Password' or contains(@name,'password')]");
    private By btnMasuk = By.xpath("//button[contains(text(),'Masuk') or @type='submit']");
    private By msgError = By.xpath("//div[contains(@class,'error') or contains(@class,'alert')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void inputUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(txtUsername));
        driver.findElement(txtUsername).clear();
        driver.findElement(txtUsername).sendKeys(username);
    }

    public void inputPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(txtPassword));
        driver.findElement(txtPassword).clear();
        driver.findElement(txtPassword).sendKeys(password);
    }

    public void klikMasuk() {
        wait.until(ExpectedConditions.elementToBeClickable(btnMasuk));
        driver.findElement(btnMasuk).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(msgError)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getPasswordInputValue() {
        return driver.findElement(txtPassword).getAttribute("value");
    }
}