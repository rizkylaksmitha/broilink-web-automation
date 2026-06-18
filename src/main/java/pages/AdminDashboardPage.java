package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPage {
    private WebDriver driver;

    // Selector spesifik untuk penanda Dashboard Admin
    private By txtJudulDashboard = By.xpath("//h1[contains(text(),'Dashboard Admin') or contains(text(),'Selamat Datang, Admin')]");

    // Selector komponen angka statistik ringkasan dan komponen Permintaan Terbaru
    private By statRingkasan = By.xpath("//div[contains(@id,'statistik') or contains(@class,'summary')]");
    private By logPermintaan = By.xpath("//div[contains(@id,'permintaan') or contains(text(),'Permintaan Terbaru')]");

    public AdminDashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method baru untuk memastikan user benar-benar mendarat di Dashboard khusus Admin
    public boolean isJudulAdminDashboardTampil() {
        try {
            return driver.findElement(txtJudulDashboard).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStatistikTampilAkurat() {
        try {
            return driver.findElement(statRingkasan).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogPermintaanRealtimeTampil() {
        try {
            return driver.findElement(logPermintaan).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}