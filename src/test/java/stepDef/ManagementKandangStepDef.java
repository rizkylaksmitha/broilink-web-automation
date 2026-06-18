package stepDef;

import pages.LoginPage;
import pages.ManagementKandangPage;
import utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ManagementKandangStepDef {

    private WebDriver driver;
    private LoginPage loginPage;
    private ManagementKandangPage kandangPage;

    @Given("Admin sudah login dan berada di halaman Manajemen Kandang")
    public void admin_sudah_login_dan_berada_di_halaman_manajemen_kandang() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(driver);
        this.kandangPage = new ManagementKandangPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl != null && currentUrl.contains("kandang")) {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.urlContains("kandang"));
            return;
        }

        if (currentUrl != null && currentUrl.contains("admin")) {
            driver.navigate().to("http://localhost:5173/admin/kandang");
            wait.until(ExpectedConditions.urlContains("kandang"));
            return;
        }

        driver.get("http://localhost:5173/");

        By btnGabung = By.xpath(
                "//button[contains(text(),'Gabung') or contains(text(),'Mulai')] | " +
                        "//a[contains(text(),'Gabung')]"
        );
        wait.until(ExpectedConditions.elementToBeClickable(btnGabung)).click();

        loginPage.inputUsername("admin");
        loginPage.inputPassword("password");
        loginPage.klikMasuk();

        wait.until(ExpectedConditions.urlContains("dashboard"));
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        driver.navigate().to("http://localhost:5173/admin/kandang");
        wait.until(ExpectedConditions.urlContains("kandang"));
    }

    @Given("Data kandang dan peternak sudah terdaftar")
    public void data_kandang_dan_peternak_sudah_terdaftar() {
    }

    @When("Admin memilih dropdown Kandang {string}")
    public void admin_memilih_dropdown_kandang(String kandang) {
        kandangPage.selectKandang(kandang);
    }

    @When("Admin memilih Peternak {string}")
    public void admin_memilih_peternak(String peternak) {
        kandangPage.selectPeternak(peternak);
    }

    @When("Admin mengklik Simpan Penugasan")
    public void admin_mengklik_simpan_penugasan() {
        kandangPage.klikSimpanPenugasan();
    }

    @Then("Hubungan relasi kerja berhasil disimpan dan sistem menampilkan notifikasi {string}")
    public void hubungan_relasi_kerja_berhasil_disimpan(String msg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String aktualTeks = "";
        try {
            org.openqa.selenium.WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(@class, 'toast') or contains(@class, 'notification') or contains(text(), '" + msg + "')]")
            ));
            aktualTeks = toast.getText();
        } catch (Exception e) {
            aktualTeks = kandangPage.getNotifikasiTeks();
        }

        if (aktualTeks.isEmpty() && driver.getPageSource().contains(msg)) {
            aktualTeks = msg;
        }

        Assertions.assertEquals(msg, aktualTeks, "Pesan notifikasi penugasan tidak sesuai!");
    }

    @When("Admin menginput angka luas {string} dan jumlah ayam {string} pada kolom")
    public void admin_menginput_angka_luas_dan_jumlah_ayam(String luas, String ayam) {
        kandangPage.fillKandangForm(luas, ayam);
    }

    @When("Admin mengklik Simpan")
    public void admin_mengklik_simpan() {
        kandangPage.klikSimpanKandang();
    }

    @Then("Data sukses disimpan dan sistem menampilkan notifikasi {string}")
    public void data_sukses_disimpan(String msg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[contains(@class, 'toast') or contains(@class, 'notification')]"), msg));
        } catch (Exception e) {
        }

        Assertions.assertEquals(msg, kandangPage.getNotifikasiTeks(), "Pesan notifikasi sukses simpan kandang salah!");
    }

    @Then("Sistem menolak simpan dan menampilkan pesan eror {string}")
    public void sistem_menolak_simpan_dan_menampilkan_pesan_eror(String errorMsg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[contains(@class, 'toast') or contains(@class, 'notification')]"), errorMsg));
        } catch (Exception e) {
        }

        Assertions.assertEquals(errorMsg, kandangPage.getNotifikasiTeks(), "Pesan eror batas minimum tidak sesuai!");
    }

    @Then("Sistem menolak simpan dan menampilkan pesan eror {string} atau {string}")
    public void sistem_menolak_simpan_dan_menampilkan_pesan_eror_atau(String err1, String err2) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[contains(@class, 'toast') or contains(@class, 'notification')]"), err1),
                    ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[contains(@class, 'toast') or contains(@class, 'notification')]"), err2)
            ));
        } catch (Exception e) {
        }

        String aktualTeks = kandangPage.getNotifikasiTeks();
        boolean isMatch = aktualTeks.contains(err1) || aktualTeks.contains(err2);

        Assertions.assertTrue(isMatch, "Pesan eror kombinasi tidak sesuai! Teks aktual: " + aktualTeks);
    }
}