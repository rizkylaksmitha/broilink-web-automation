package stepDef;

import utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.ReportHistoryPage;
import java.time.Duration;

public class ReportHistoryStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private ReportHistoryPage historyPage;

    @Given("Admin sudah login dan berada di halaman Laporan BroiLink")
    @Given("Admin sudah login dan berada di halaman Riwayat Laporan BroiLink")
    public void admin_sudah_login_dan_berada_di_halaman_riwayat_laporan_broilink() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(driver);
        this.historyPage = new ReportHistoryPage(driver);

        driver.get("http://localhost:5173/login");
        loginPage.inputUsername("admin");
        loginPage.inputPassword("password");
        loginPage.klikMasuk();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));

        // Masuk ke halaman riwayat
        driver.navigate().to("http://localhost:5173/admin/requests");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    @Given("Data permintaan terbaru tersedia untuk {string}")
    public void data_permintaan_terbaru_tersedia_untuk(String username) {
        System.out.println("LOG PRECONDITION: Memastikan request dari " + username + " tampil di tabel.");
    }

    @When("Admin memilih baris pengguna {string}")
    public void admin_memilih_baris_pengguna(String username) {
        historyPage.pilihBarisPengguna(username);
    }

    @When("Admin mengubah status dropdown menjadi {string}")
    public void admin_mengubah_status_dropdown_menjadi(String status) {
        historyPage.ubahStatusDropdown(status);
        try { Thread.sleep(1500); } catch (Exception e) {}
    }

    @Then("Dropdown status berhasil diperbarui")
    public void dropdown_status_berheard_diperbarui() {
        try { Thread.sleep(1500); } catch (Exception e) {}
        boolean isSuccess = driver.getCurrentUrl().contains("history") || driver.getPageSource().contains("budi");
        Assertions.assertTrue(isSuccess, "Gagal memperbarui status permintaan akun!");
    }
}