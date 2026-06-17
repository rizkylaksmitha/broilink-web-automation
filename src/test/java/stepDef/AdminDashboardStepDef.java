package stepDef;

import pages.LoginPage;
import pages.AdminDashboardPage;
import utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions; // JUnit 5 Assertions
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AdminDashboardStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private AdminDashboardPage dashboardPage;

    @Given("Admin sudah login dan berada di halaman Dashboard")
    public void admin_sudah_login_dan_berada_di_halaman_dashboard() {
        this.driver = DriverManager.getDriver();
        if (this.driver != null) {
            this.driver.manage().deleteAllCookies();
        }
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new AdminDashboardPage(driver);

        driver.get("http://localhost:5173/login");
        loginPage.inputUsername("admin");
        loginPage.inputPassword("password");
        loginPage.klikMasuk();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }

    @When("Admin memeriksa data angka pada ringkasan statistik")
    public void admin_memeriksa_data_angka_pada_ringkasan_statistik() {
    }

    @Then("Angka tampil akurat")
    public void angka_tampil_akurat() {
        // JUnit 5 posisinya: Assertions.assertTrue(kondisi, pesan_error);
        Assertions.assertTrue(dashboardPage.isStatistikTampilAkurat(),
                "Gagal! Komponen statistik ringkasan tidak tampil di layar dashboard.");
    }

    @When("Admin memeriksa komponen {string}")
    public void admin_memeriksa_komponen(String komponen) {
    }

    @Then("Menampilkan antrean log secara real-time")
    public void menampilkan_antrean_log_secara_real_time() {
        // JUnit 5 posisinya: Assertions.assertTrue(kondisi, pesan_error);
        Assertions.assertTrue(dashboardPage.isLogPermintaanRealtimeTampil(),
                "Gagal! Komponen log permintaan real-time tidak tampil di layar dashboard.");
    }
}