package stepDef;

import pages.LoginPage;
import utils.DriverManager;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginStepDef {
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("Halaman login terbuka")
    public void halaman_login_terbuka() {
        // Pemicu Driver langsung diambil di sini saat langkah pertama berjalan
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(this.driver);

        driver.get("http://localhost:5173/login");

        // KITA KASIH JEDA MANUAL 3 DETIK (Hanya untuk debugging)
        // Biar kita yakin halamannya selesai loading total sebelum diketik
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @When("User memasukkan username {string} dan password {string}")
    public void user_memasukkan_username_dan_password(String username, String password) {
        // Kita paksa ketik langsung di sini jika loginPage bermasalah
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
    }

    @When("User mengklik tombol Masuk")
    public void user_mengklik_tombol_masuk() {
        loginPage.klikMasuk();
    }

    @Then("Berhasil login dan masuk ke Dashboard Admin")
    public void berhasil_login_dan_masuk_ke_dashboard_admin() {
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Then("Sistem menampilkan pesan error login")
    public void sistem_menampilkan_pesan_error_login() {
        Assert.assertFalse(loginPage.getErrorMessage().isEmpty());
    }

    @Then("Sistem menolak login atau menampilkan pesan validasi")
    public void sistem_menolak_login_atau_menampilkan_pesan_validasi() {
        Assert.assertFalse(loginPage.getErrorMessage().isEmpty());
    }

    @Then("Sistem menerima input password")
    public void sistem_menerima_input_password() {
        Assert.assertTrue(loginPage.getPasswordInputValue().length() >= 6);
    }
}