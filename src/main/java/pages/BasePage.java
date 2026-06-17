package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Menambahkan Explicit Wait 15 detik agar pengetesan lebih stabil saat loading local host XAMPP
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Fungsi pembungkus untuk klik elemen (Menunggu hingga elemen siap diklik)
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Fungsi pembungkus untuk mengetik teks (Membersihkan kolom terlebih dahulu)
    protected void writeText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    // Fungsi pembungkus untuk mengambil teks dari elemen web
    protected String readText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // Fungsi pembungkus untuk memeriksa apakah elemen muncul di layar
    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Fungsi pembungkus untuk memilih opsi pada Dropdown Select
    protected void selectDropdownByText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    public void quit(){
        driver.quit();
    }
}